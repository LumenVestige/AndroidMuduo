#ifndef MUDUO_EXAMPLES_SIMPLE_ECHO_ECHO_H
#define MUDUO_EXAMPLES_SIMPLE_ECHO_ECHO_H

#include "muduo/net/TcpServer.h"

// RFC 862
class EchoServer {
public:
    using EchoCallback = std::function<void(const std::string &)>;

    EchoServer(muduo::net::EventLoop *loop,
               const EchoCallback &callback,
               const muduo::net::InetAddress &listenAddr);

    void start();  // calls server_.start();

private:
    void onConnection(const muduo::net::TcpConnectionPtr &conn);

    void onMessage(const muduo::net::TcpConnectionPtr &conn,
                   muduo::net::Buffer *buf,
                   muduo::Timestamp time);

    EchoCallback callback_;
    muduo::net::TcpServer server_;
};

#endif  // MUDUO_EXAMPLES_SIMPLE_ECHO_ECHO_H
