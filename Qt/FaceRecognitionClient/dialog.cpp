#include "dialog.h"

Dialog::Dialog(QWidget *parent)
    : QDialog(parent)
{  QVBoxLayout * mainLayout = new QVBoxLayout(this);
    // 底下的布局对象
    topLayout = new QHBoxLayout(this);
    bottomLayout = new QHBoxLayout(this);
    // 上传按钮
    upLoadFaceButton = new QPushButton(QObject::tr("上传人脸"),this);
    checkFaceButton = new QPushButton(QObject::tr("识别人脸"),this);
    imgLabel = new QLabel(this);
    //添加弹簧
    bottomLayout->addStretch();
    bottomLayout->addWidget(upLoadFaceButton);
    bottomLayout->addWidget(checkFaceButton);
    bottomLayout->addStretch();

    topLayout->addStretch();
    topLayout->addWidget(imgLabel);
    topLayout->addStretch();
    setWindowTitle("人脸识别客户端");
    mainLayout->addLayout(topLayout);
    mainLayout->addLayout(bottomLayout);
    setMinimumSize(1200,800);
    setLayout(mainLayout);
    QString applicationDirPath = QCoreApplication::applicationDirPath()+"/haarcascade_frontalface_alt2.xml";
    applicationDirPath = applicationDirPath.replace(QRegExp("/"), "\\");
    faceCascadeXmlPath = applicationDirPath.toStdString();
    if(!faceCascade.load(faceCascadeXmlPath)){
        QMessageBox::critical(NULL, "警告", "人脸检测模块失效，请将程序安装目录转移到非中文目录下重试", QMessageBox::Yes | QMessageBox::No, QMessageBox::Yes);
        exit(1);
    }
    timer = new QTimer;
    cap = new VideoCapture();

    connect(timer, SIGNAL(timeout()), this, SLOT(showCamera()));//时间到后启动opencam时间
    connect(upLoadFaceButton, SIGNAL(clicked()), this, SLOT(uploadFace()));//响应
    connect(checkFaceButton, SIGNAL(clicked()), this, SLOT(checkFace()));//响应
    timer->start(10);
    //连接上传线程的收到消息信号
    connect(&uploadImgThread, SIGNAL(recvedResultSignal(ResultProto)), this, SLOT(recvResult(ResultProto)));//响应
}

Dialog::~Dialog()
{
    timer->stop();
    killTimer(timer->timerId());
    cap->release();
}

void Dialog::showCamera(){
    if (!cap->isOpened())
    {
        cap->open(0);
    };
    cap->read(frame);
    cv::flip(frame,frame,1);
    faceRect.width = frame.cols*0.4;
    faceRect.height = frame.rows*0.7;
    faceRect.x = frame.cols*0.3;
    faceRect.y = frame.rows*0.15;
    rectangle(frame, faceRect, Scalar(0, 0, 255), 2, 8, 0);
    QImage img = QImage((const unsigned char*)frame.data, // uchar* data
                        frame.cols, frame.rows, frame.step, QImage::Format_RGB888);//Format_RGB888格式转换
    int a = this->width();
    int b = this->height();
    img = img.rgbSwapped();
    QImage ime = img.scaled(a, b,Qt::KeepAspectRatio, Qt::SmoothTransformation);//自定义缩放
    imgLabel->setMaximumSize(ime.width(),ime.height());
    imgLabel->setPixmap(QPixmap::fromImage(ime));
    imgLabel->setScaledContents(true);
}
void Dialog::uploadFace(){
    if(uploadImgThread.isRunning()) return;
    timer->stop();
    qDebug()<<uploadImgThread.isRunning();
    qDebug()<<"上传人脸";
    // 截取指定位置的人脸图像
    Mat face_img,faceRectMat;
    faceRectMat = frame(faceRect);
    cvtColor(faceRectMat, faceRectMat, COLOR_BGR2RGB);
    // 目标检测从face_img摘出人脸
    cvtColor(faceRectMat, gray_img, COLOR_RGB2GRAY);
    equalizeHist(gray_img, gray_img);
    vector<Rect> faces;                  // faces是一个容器，用来接收检测到的人脸
    faceCascade.detectMultiScale(gray_img, faces, 1.1, 2, 0, Size(10, 10), Size(face_img.rows,face_img.cols));  //寻找人脸
    if(faces.size()>0){
        qDebug()<<"识别到人脸了";
        bool ok;
        QString personName = QInputDialog::getText(this, tr("请输入你的名字:"),
                                                   tr("名字:"), QLineEdit::Normal/*QLineEdit::NoEcho*/,
                                                   "", &ok);
        if (!ok || personName.isEmpty()){
            QMessageBox::information(NULL,"提示","取消上传");
            timer->start();
            return;
        }
        faceRectMat(faces[0]).copyTo(face_img);
        cv::imwrite("checkFace.jpg", face_img);
        uploadImgThread.setImage(faceRectMat.clone(),personName);
        qDebug()<<"开启线程";
        uploadImgThread.start();
    }else{
        // 没有识别出人脸
        QMessageBox::information(this, "识别结果","把脸放在红框内");
    }
    timer->start();
}

void Dialog::checkFace(){
    QDateTime dateTime(QDateTime::currentDateTime());
    QString qStr = dateTime.toString("yyy-MM-dd hh:mm::ss ddd");
    qDebug()<<qStr;
    if(uploadImgThread.isRunning()) return;
    qDebug()<<uploadImgThread.isRunning();
    qDebug()<<"识别人脸";
    // 截取指定位置的人脸图像
    Mat face_img,faceRectMat;
    faceRectMat = frame(faceRect);
    cvtColor(faceRectMat, faceRectMat, COLOR_BGR2RGB);
    // 目标检测从face_img摘出人脸
    cvtColor(faceRectMat, gray_img, COLOR_RGB2GRAY);
    equalizeHist(gray_img, gray_img);
    vector<Rect> faces;                  // faces是一个容器，用来接收检测到的人脸
    faceCascade.detectMultiScale(gray_img, faces, 1.1, 2, 0, Size(10, 10), Size(face_img.rows,face_img.cols));  //寻找人脸
    if(faces.size()>0){
        qDebug()<<"识别到人脸了";
        uploadImgThread.setImage(faceRectMat.clone());
        uploadImgThread.start();
    }else{
        // 没有识别出人脸
        QMessageBox::information(this, "识别结果","把脸放在红框内");
    }
}

void Dialog::recvResult(ResultProto result){
    QDateTime dateTime(QDateTime::currentDateTime());
    QString qStr = dateTime.toString("yyy-MM-dd hh:mm::ss ddd");
    qDebug()<<qStr;
    QMessageBox::information(this, "识别结果",result.message().c_str());
}
