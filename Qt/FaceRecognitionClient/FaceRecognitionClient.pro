QT       += core gui
QT       += network
greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++11

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    ImageProto.pb.cc \
    ResultProto.pb.cc \
    main.cpp \
    dialog.cpp \
    uploadimagethread.cpp

HEADERS += \
    ImageProto.pb.h \
    ResultProto.pb.h \
    dialog.h \
    uploadimagethread.h

TRANSLATIONS += \
    FaceRecognitionClient_zh_CN.ts

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target



#配置protobuf
INCLUDEPATH += "D:/protobuf/protobuf-cpp-3.1.0/src"
win32: LIBS += -L$$PWD/Debug/ -llibprotobufd

INCLUDEPATH += $$PWD/Debug
DEPENDPATH += $$PWD/Debug


win32: LIBS += -L$$PWD/Release/ -llibprotobuf

INCLUDEPATH += $$PWD/Release
DEPENDPATH += $$PWD/Release


#opencv的路径
INCLUDEPATH += D:\opencv\opencv-2.4.11\build\include
               D:\opencv\opencv-2.4.11\build\include\opencv
               D:\opencv\opencv-2.4.11\build\include\opencv2


win32:CONFIG(debug, debug|release): {
LIBS += -LD:/opencv/opencv-2.4.11/build/x64/vc12/lib \
    -lopencv_core2411d \
    -lopencv_imgproc2411d \
    -lopencv_highgui2411d \
    -lopencv_ml2411d \
    -lopencv_video2411d \
    -lopencv_features2d2411d \
    -lopencv_calib3d2411d \
    -lopencv_objdetect2411d \
    -lopencv_contrib2411d \
    -lopencv_legacy2411d \
    -lopencv_flann2411d
} else:win32:CONFIG(release, debug|release): {
LIBS += -LD:/opencv/opencv-2.4.11/build/x64/vc12/lib \
    -lopencv_core2411 \
    -lopencv_imgproc2411 \
    -lopencv_highgui2411 \
    -lopencv_ml2411 \
    -lopencv_video2411 \
    -lopencv_features2d2411 \
    -lopencv_calib3d2411 \
    -lopencv_objdetect2411 \
    -lopencv_contrib2411 \
    -lopencv_legacy2411 \
    -lopencv_flann2411
}

