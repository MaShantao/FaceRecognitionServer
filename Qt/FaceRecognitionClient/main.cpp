#include "dialog.h"
#include <QMetaType>
#include <QApplication>

int main(int argc, char *argv[])
{
    qRegisterMetaType<ResultProto>("ResultProto");
    QApplication a(argc, argv);
    Dialog w;
    w.show();
    return a.exec();
}
