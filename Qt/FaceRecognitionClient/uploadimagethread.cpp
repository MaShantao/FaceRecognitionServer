#include "uploadimagethread.h"

UploadImageThread::UploadImageThread()
{

}
UploadImageThread::~UploadImageThread(){

}
void UploadImageThread::setImage(cv::Mat mat,QString personName){
    imageProto.set_personname("null");
    imageProto.set_cols(mat.cols);
    imageProto.set_rows(mat.rows);
    imageProto.set_personname(personName.toStdString());
    imageProto.clear_data();
    for(int i = 0;i <mat.cols*mat.rows*3;i++){
        imageProto.add_data(mat.data[i]);
    }
}

google::protobuf::uint32 UploadImageThread::readMessageSize(char *byteCountbuf){
    google::protobuf::uint32 size;
    google::protobuf::io::ArrayInputStream ais(byteCountbuf, 4);
    CodedInputStream coded_input(&ais);
    coded_input.ReadVarint32(&size);//Decode the HDR and get the size
    return size;
}

void UploadImageThread::readBody(QTcpSocket *clientSocket,google::protobuf::uint32 size, ResultProto &result){
    int bytecount;
    char *buffer = new char[size + 4];
    // 读取整个缓冲区
    try{
        if ((bytecount =clientSocket->read(buffer, 4 + size)) == -1){
            qDebug() << "接受数据失败 " << size << endl;
        }
    }
    catch (double e){
        qDebug() << "捕获到异常 " << size << endl;
    }
    //为输入分配足够的内存
    google::protobuf::io::ArrayInputStream ais(buffer, size + 4);
    CodedInputStream coded_input(&ais);
    //读取Varint编码的无符号整形数字。不超过32位
    coded_input.ReadVarint32(&size);
    //在读取完消息的长度之后，设置读取数据的长度限制
    google::protobuf::io::CodedInputStream::Limit msgLimit = coded_input.PushLimit(size);
    //反序列化
    result.ParseFromCodedStream(&coded_input);
    //序列化完消息之后，调用PopLimit（）来撤消限制
    coded_input.PopLimit(msgLimit);
}


void UploadImageThread::run(){
    ResultProto result;
    QTcpSocket *clientSocket = new QTcpSocket();
    clientSocket->connectToHost(host, port);
    if(clientSocket->isValid()){
        int size = imageProto.ByteSizeLong() + 4;
        char *message = new char[size];
        google::protobuf::io::ArrayOutputStream aos(message, size);
        google::protobuf::io::CodedOutputStream *coded_output = new google::protobuf::io::CodedOutputStream(&aos);
        // 首先写入序列化之后数据的长度
        coded_output->WriteVarint32(imageProto.ByteSizeLong());
        // 序列化
        imageProto.SerializeToCodedStream(coded_output);
        int sendRe = clientSocket->write(message, size);
        clientSocket->waitForBytesWritten();
        clientSocket->flush();
        if( -1 == sendRe){
            result.set_status(400);
            result.set_message("客户端发送数据失败！");
        }
        clientSocket->waitForReadyRead(-1);
        char countBuffer[4];
        memset(countBuffer, '\0', 4);
        int bytecount;
        // 一直等待服务器回信
        while (true){
            // 读取服务器给回复信息,countBuffer存的是长度
            // protobuf消息的前四个字节是数据包的长度
            bytecount = clientSocket->peek(countBuffer, 4);
            if (bytecount == 0){ break; }
            if (bytecount == -1){
                result.set_status(404);
                result.set_message("未知服务器和端口");
                qDebug()<<"读取数据出错:"<<result.status()<<result.message().c_str();
                break;
            }
            if (bytecount > 0){
                // 读取服务器回复的消息体
                readBody(clientSocket,readMessageSize(countBuffer), result);
                qDebug()<<"检测到消息:"<<result.status()<<result.message().c_str();
                break;
            }
        }
    }
    else{
        result.set_status(400);
        result.set_message("套接字无效！");
    }
    emit(recvedResultSignal(result));
    clientSocket->disconnectFromHost();
    clientSocket->close();
    delete clientSocket;
}
