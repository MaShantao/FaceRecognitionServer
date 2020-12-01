#pragma execution_character_set("utf-8")
#ifndef DIALOG_H
#define DIALOG_H

#include <QDialog>
#include <QVBoxLayout>
#include <QDir>
#include <QCoreApplication>
#include <QPushButton>
#include <QHBoxLayout>
#include <QLabel>
#include <QImage>
#include <QGridLayout>
#include <QTcpSocket>
#include <QInputDialog>
// opencv头文件
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/opencv.hpp>
#include "uploadimagethread.h"
// 定时器
#include <QTimer>
#include <QDebug>
#include <QMessageBox>
#include <QDateTime>
//proto对象
#include "ImageProto.pb.h"
#include "ResultProto.pb.h"
#include <thread>
#include<vector>

using namespace cv;
using namespace std;

class Dialog : public QDialog
{
    Q_OBJECT

public:
    Dialog(QWidget *parent = nullptr);
    ~Dialog();
signals:
    void emitSaveTOsignal(ResultProto);

private slots:
    void showCamera();
    void uploadFace();
    void checkFace();
    void recvResult(ResultProto result);
private:
    QLabel *imgLabel; //显示摄像头的标签控件
    VideoCapture *cap;
    Mat frame;
    Mat gray_img;
    Mat face_img;
    QTimer *timer;// 定时器
    QHBoxLayout *topLayout;//上布局
    QHBoxLayout *bottomLayout;//下布局
    QPushButton *upLoadFaceButton;//上传人脸的按钮
    QPushButton *checkFaceButton;//识别人脸的按钮
    CascadeClassifier faceCascade; //人脸级联分类器
    Rect faceRect;
    UploadImageThread uploadImgThread;
    std::string faceCascadeXmlPath;
};
#endif // DIALOG_H
