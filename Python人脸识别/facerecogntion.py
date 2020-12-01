# -*- coding: utf-8 -*-
import face_recognition
import sys
import redis
from datetime import datetime
import time
from PIL import Image
import os
import uuid
import numpy as np
from numpy import float64
import json
from multiprocessing import Process
from multiprocessing import Pool

def recognize_face(megQueueStr):
    print("开始人脸识别的进程,监听消息队列:",megQueueStr)
    redisCli = redis.StrictRedis(host='127.0.0.1', port=6379)
    while(True):
        queueMsg = redisCli.lpop(megQueueStr)
        time.sleep(0.001)
        if queueMsg is not None:
            print("识别人脸...")
            queueMsg = str(queueMsg, encoding='UTF-8')
            imgKey, resultKey = queueMsg.split("_")
            result_status = 200
            result_msg = ""
            # 1.加载未知人脸图片
            redisCli = redis.Redis(host='127.0.0.1', port=6379)
            cols = int(redisCli.hget(imgKey, "cols"))
            rows = int(redisCli.hget(imgKey, "rows"))
            data = redisCli.hget(imgKey, "data")
            unknown_face = np.array(json.loads(data), dtype=np.uint8)
            unknown_face = unknown_face.reshape(rows, cols, 3)
            redisCli.delete(imgKey)
            # 2.对未知人脸图片进行编码
            unknown_face_encodings = face_recognition.face_encodings(unknown_face)
            if len(unknown_face_encodings) == 0:
                redisCli.set(resultKey, str(500) + "_" + "未检测到人脸")
                continue
            unknown_face_encoding = unknown_face_encodings[0]
            # 3.加载所有已知人脸
            known_faces_encoding = []
            face_infos = redisCli.lrange('face_infos', 0, -1)
            for face_info in face_infos:
                face_info = eval(str(face_info, encoding='utf-8'))
                face_encoding = np.array(json.loads(face_info[1]))
                known_faces_encoding.append(face_encoding);
            if len(known_faces_encoding)==0:
                redisCli.set(resultKey, str(500) + "_" + "人脸库为空，先录入人脸")
                continue
            # 4.识别人脸
            face_distances = face_recognition.face_distance(known_faces_encoding, unknown_face_encoding)
            min_index = np.argmin(face_distances)
            if face_distances[min_index] <= 0.4:
                result_msg = eval(str(face_infos[min_index], encoding='utf-8'))[0]
            else:
                result_msg = "未知人脸"
            # 5.返回结果
            redisCli.set(resultKey, str(result_status) + "_" + result_msg)
            print("结果:", str(result_status) + "_" + result_msg)

def upload_faceid(megQueueStr):
    print("开始上传人脸id的进程，监听消息队列:",megQueueStr)
    redisCli = redis.StrictRedis(host='127.0.0.1', port=6379)
    while(True):
        queueMsg = redisCli.lpop(megQueueStr)
        time.sleep(0.001)
        if  queueMsg is not None:
            print("上传人脸...")
            queueMsg = str(queueMsg, encoding='UTF-8')
            imgKey,resultKey = queueMsg.split("_")
            result_status = 200
            result_msg = "人脸录入成功"
            personName = str(redisCli.hget(imgKey, "personName"), encoding='UTF-8')
            cols = int(redisCli.hget(imgKey, "cols"))
            rows = int(redisCli.hget(imgKey, "rows"))
            data = redisCli.hget(imgKey, "data")
            upload_image = np.array(json.loads(data), dtype=np.uint8)
            upload_image = upload_image.reshape(rows, cols, 3)
            # 将人脸删除
            redisCli.delete(imgKey)
            # 2.对人脸图片进行编码，只要第一张人脸。
            image_face_encodings = face_recognition.face_encodings(upload_image)
            if len(image_face_encodings) == 0:
                redisCli.set(resultKey, str(500) + "_" + "未检测到人脸")
                continue
            image_face_encoding = image_face_encodings[0]
            # 3.将人脸图片截取保存到文件夹
            top, right, bottom, left = face_recognition.face_locations(upload_image)[0]
            face_img = upload_image[top:bottom, left:right]
            pil_img = Image.fromarray(face_img)
            # 3.1 生成随机的文件名，拼接保存路径
            save_name = str(uuid.uuid1())
            save_path = "/FaceRecognition/pictures_of_people_i_know/" + personName;
            if not os.path.exists(save_path):
                os.makedirs(save_path)
            save_path += "/" + save_name + ".jpg"
            # 3.2保存人脸图片
            pil_img.save(save_path);
            # 4.将人脸的128维编码信息存到redis中
            face_info = [personName, str(image_face_encoding.tolist()), "save_path"]
            redisCli.rpush("face_infos", str(face_info));
            redisCli.set(resultKey,str(result_status)+"_"+result_msg)
            print("结果:",str(result_status)+"_"+result_msg)

if __name__ == '__main__':
    uploadProcessCount = 10
    recognitionProcessCount = 50
    processPool = Pool(uploadProcessCount+recognitionProcessCount)
    #创建人脸识别的线程池
    for i in range(recognitionProcessCount):
        processPool.apply_async(recognize_face, ("face_recogintion_queue_"+str(i),))
    #创建上传人脸的进程池
    for i in range(uploadProcessCount):
        processPool.apply_async(upload_faceid, ("upload_faceid_queue_"+str(i),))
    # 关闭子进程池
    processPool.close()
    # 等待所有子进程结束
    processPool.join()

