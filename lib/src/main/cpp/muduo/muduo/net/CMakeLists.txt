include(CheckFunctionExists)

check_function_exists(accept4 HAVE_ACCEPT4)
if (NOT HAVE_ACCEPT4)
    set_source_files_properties(SocketsOps.cc PROPERTIES COMPILE_FLAGS "-DNO_ACCEPT4")
endif ()

set(net_SRCS
        Acceptor.cc
        Buffer.cc
        Channel.cc
        Connector.cc
        EventLoop.cc
        EventLoopThread.cc
        EventLoopThreadPool.cc
        InetAddress.cc
        Poller.cc
        poller/DefaultPoller.cc
        poller/EPollPoller.cc
        poller/PollPoller.cc
        Socket.cc
        SocketsOps.cc
        TcpClient.cc
        TcpConnection.cc
        TcpServer.cc
        Timer.cc
        TimerQueue.cc
)

add_library(muduo_net STATIC ${net_SRCS})
target_link_libraries(muduo_net
        muduo_base
        android
        log)

#set(HEADERS
#  Buffer.h
#  Callbacks.h
#  Channel.h
#  Endian.h
#  EventLoop.h
#  EventLoopThread.h
#  EventLoopThreadPool.h
#  InetAddress.h
#  TcpClient.h
#  TcpConnection.h
#  TcpServer.h
#  TimerId.h
#  )

#add_subdirectory(http)
#add_subdirectory(inspect)
