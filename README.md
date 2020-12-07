# FaceRecognitionServer

此项目来自于我的CSDN专栏文章《从零开始写高性能的人脸识别服务器》，本项目重心在于高性能，前端写的比较low，欢迎大家一键三连，批评指正。下面给出专栏地址：

[从零开始写高性能的人脸识别服务器（一）](https://blog.csdn.net/qq_34037358/article/details/110426408)

[从零开始写高性能的人脸识别服务器（二）](https://blog.csdn.net/qq_34037358/article/details/110428524)

[从零开始写高性能的人脸识别服务器（三）](https://blog.csdn.net/qq_34037358/article/details/110797105)

[从零开始写高性能的人脸识别服务器（四）](https://blog.csdn.net/qq_34037358/article/details/110797939)

​	如今AI的应用越来越广，但是对于AI产品技术的部署落地是一个很多人都会忽视的地方，因为大部分的AI服务都是基于Python的，将基于Python的AI程序如何部署到Web或者其他平台是一个问题，本专栏将从零开始写一个高性能支持高并发的人脸识别服务器。因为本人的技术栈是Java，所以使用Java的高性能网络IO模型库Netty进行服务器的开发，AI的模型大部分都是Python进行开发的，所以也使用了基于Python的人脸识别框架**face_recoginize**用来开发人脸识别的微服务。由于AI模型的加载一般都很耗时，所以把Python的AI程序做成微服务，服务器和AI微服务之间通过redis队列进行通信。

## 1 技术选型

Java高性能网络模型框架首选Netty。人脸识别程序选用基于Python的人脸识别库**face_recoginize**.选用Redis做消息队列。因为模型在预加载是会很耗时，所以将Python人脸识别程序包装成微服务。

由于Python全局解释器锁（GIL）的存在，解释器解释执行任何 Python 代码时，都需要先获得这把锁才行，在遇到 I/O 操作时会释放这把锁。如果是纯计算的程序，没有 I/O 操作，解释器会每隔 100 次操作就释放这把锁，让别的线程有机会执行（这个次数可以通过 sys.setcheckinterval 来调整）。所以虽然 CPython 的线程库直接封装操作系统的原生线程，但 CPython 进程做为一个整体，同一时间只会有一个获得了 GIL 的线程在跑，其它的线程都处于等待状态等着 GIL 的释放。也就是说，对Python而言，计算密集型的多线程，其实性能和单线程是一样的。解决方法就是同时运行多个Python程序，也就是采用多进程的方式。

Java服务器收到数据之后，将数据存储在redis的key1中，此时产生一个key2（存储Python程序的处理结果）。Java使用随机数获取随机的处理进程。将key1存储到该进程监听的队列中，然后Java监听key2获取Python进程的处理结果，拿到结果之后返回给客户端。

客户端我写了C的和Html的。C的程序使用的是**Qt5.9.9** + **opencv-2.4.11** + **protobuf-3.1.0**， 之所以这样选，是因为之前是基于QT5.14开发的，后面发现程序写完之后发给导师，导师的电脑是win7，程序打开就崩溃，这也是为啥都说华为现在还在用VS2013的原因，因为只有MSVC2013版本的才可以兼容win7和win10。然后选择MSVC2013之后，protobuf的版本就不能太高，因为高版本的protobuf都是基于C++11的高级特征，MSVC只支持一小部分的C++11特征，所以使用高版本的会因为不兼容而出错。

因为本菜鸡还不懂架构的一些东西，所以这里的Redis做消息队列处理的比较垃圾，架构很垃圾。基本结构如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201125633611.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)

## 2 环境准备

​	技术选型定好之后，我们接下来准备环境，Java的环境不用具体介绍了，编译环境我用的是idea，基于maven开发的。下面我们根据教程来做安装，如果不想做C客户端的可以绕过QT的那一部分。

### 2.1 安装**face_recoginize**

需要注意的是**一定要用Python3.6版本** ，版本不符合会出现各种各样的问题,dlib版本一定要选择19.7.0的，要不然会出现各种问题。下面介绍一下win10环境下的安装。linux下的[安装地址](https://blog.csdn.net/weixin_40450867/article/details/81734815)

（1）创建anaconda python3.6环境

```shell
conda create --name your_env_name python==3.6.0
```

（2）安装dlib

必须是19.7.0版本，不要直接写“pip install dlib”。这样会安装最新的版本。

```shell
pip install dlib==19.7.0
```

（3）安装face_recognition

```shell
pip install face_recognition
```

至此就大功告成了。

### 2.2 安装Redis

本项目中用到了Redis，所以大家需要安装redis，linux环境下的直接百度搜安装教程一大堆。window版本的git:[下载地址](https://github.com/MicrosoftArchive/redis/releases)。安装完之后，Python要安装redis模块。

### 2.3 Qt5.9.9的安装

这里是安装C客户端的程序，如果不需要客户端的程序，可以直接跳过。

Qt安装直接去百度搜就可以搜到一大堆，在安装的时候选择MSVC2013。需要注意的是，Qt想开发MSVC环境下编译的程序，**必须要下载相应版本的VS2013，要不然没法进行编译**，Qt5.9.9使用的是MSVC2013，所以要下载vs2013。

没用过Qt的小伙伴可能会遇到Debug不可以调试的错误，Qt项目页选择MinGW编译器方式，编译debug和release版本运行后都可以正常运行，如果是MSVC编译器方式，release版本编译后能正常运行，debug版本编译正常，但是运行会异常退出，调试弹出The CDB process terminated提示框。

解决方法是安装安装CDB调试器时，下载[Windows SDK安装包](https://developer.microsoft.com/zh-cn/windows/downloads/sdk-archive)，只需要安装其中的"Debugging Tools for Windows“。如果在遇到其他问题，就百度一下解决方案，这个项目是半年前写的，这段时间有时间我就开源，一些细节啥的忘记了。只记得[这篇博客](https://blog.csdn.net/HuntCode/article/details/94552935)帮了我很多。

## 3 效果演示

前端写的比较简陋粗糙，因为重心不在前端页面上。注意Web展示的时候我用的本地的html。如果想要在网络地址中使用电脑摄像头，网络地址得支持Https协议。

### 3.1 Web端展示

由于CSDN上传Gif文件有大小限制，所以我这里直接上截图了

（1）请求摄像头权限

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020120112485482.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)
(2)点击拍照，截取图像


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201125230923.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)
(3)上传人脸

点击上传人脸，在弹出的对话框里面输入自己的姓名


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201125300453.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)
（4）点击确定显示上传成功


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201125505311.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)

(5)点击识别，进行人脸识别


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201125536356.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)

### 3.2 Qt客户端展示

Qt客户端使用了OpenCv调取电脑摄像头。

（1）上传人脸


![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201125556354.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)

（2）人脸识别

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201125619231.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MDM3MzU4,size_16,color_FFFFFF,t_70#pic_center)