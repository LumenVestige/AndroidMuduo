#include <jni.h>
#include <string>
#include "echo.h"
#include "muduo/net/EventLoop.h"
#include "muduo/base/Logging.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_sanyinchen_lib_NativeLocalTcpServer_echoServerStart(
        JNIEnv *env,
        jobject /* this */) {
    muduo::Logger::setLogLevel(muduo::Logger::TRACE);
    muduo::net::EventLoop loop;
    muduo::net::InetAddress listenAddr(2007);
    EchoServer server(&loop, listenAddr);
    server.start();
    loop.loop();
    return env->NewStringUTF("");
}