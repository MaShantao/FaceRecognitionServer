#pragma execution_character_set("utf-8")
#ifndef UPLOADIMAGETHREAD_H
#define UPLOADIMAGETHREAD_H
#include <QThread>
#include "ImageProto.pb.h"
#include "ResultProto.pb.h"
// opencv头文件
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/opencv.hpp>

#include <QTcpSocket>

#include <google/protobuf/message.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/io/zero_copy_stream_impl.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/io/zero_copy_stream_impl_lite.h>
using namespace google::protobuf::io;

class UploadImageThread: public QThread
{
    Q_OBJECT
public:
    UploadImageThread();
    ~UploadImageThread();
    void  setImage(cv::Mat mat,QString personName = "");
signals:
    void recvedResultSignal(ResultProto result);
protected:
    void run();
    void readBody(QTcpSocket * clientSocket,google::protobuf::uint32 size,ResultProto &result);
    google::protobuf::uint32 readMessageSize(char *byteCountbuf);

private:
    ImageProto imageProto;
//    QString host = "119.3.138.73";
    QString host = "localhost";
    int port = 6666;
};

#endif // UPLOADIMAGETHREAD_H
