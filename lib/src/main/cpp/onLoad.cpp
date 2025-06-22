#include <jni.h>
#include <string>
#include "business/echo/echo.h"
#include "muduo/net/EventLoop.h"
#include "muduo/base/Logging.h"


void onEchoMessage(JNIEnv *env, const std::string &msg) {
    // 找到 NativeEchoTcpServer 的类
    jclass cls = env->FindClass("com/sanyinchen/lib/NativeEchoTcpServer");
    if (!cls) {
        // 找不到类
        return;
    }
    jfieldID instanceField = env->GetStaticFieldID(cls, "INSTANCE",
                                                   "Lcom/sanyinchen/lib/NativeEchoTcpServer;");
    jobject instance = env->GetStaticObjectField(cls, instanceField);
    // 找到静态方法 onLogMessageCallback 的方法 ID
    jmethodID mid = env->GetMethodID(cls, "onEchoMessage", "(Ljava/lang/String;)V");
    if (!mid) {
        // 找不到方法
        return;
    }

    // 构造 Java 字符串
    jstring jmsg = env->NewStringUTF(msg.c_str());

    // 调用静态方法
    env->CallVoidMethod(instance, mid, jmsg);

    // 释放局部引用
    env->DeleteLocalRef(jmsg);
    env->DeleteLocalRef(cls);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sanyinchen_lib_NativeEchoTcpServer_echoServerStart(
        JNIEnv *env,
        jobject /* this */) {
    muduo::Logger::setLogLevel(muduo::Logger::TRACE);
    muduo::net::EventLoop loop;
    muduo::net::InetAddress listenAddr(2007);
    EchoServer server(&loop, [&](const std::string &msg) {
        onEchoMessage(env, msg);
    }, listenAddr);
    server.start();
    loop.loop();
    return env->NewStringUTF("");
}
