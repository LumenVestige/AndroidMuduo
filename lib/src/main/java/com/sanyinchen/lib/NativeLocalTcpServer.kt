package com.sanyinchen.lib

object NativeLocalTcpServer {
    init {
        System.loadLibrary("local-tcp")
    }

    external fun echoServerStart(): String


}