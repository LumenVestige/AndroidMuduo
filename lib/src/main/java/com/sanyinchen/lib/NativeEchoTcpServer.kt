package com.sanyinchen.lib

import android.util.Log
import java.lang.ref.WeakReference


interface EchoMsgCallback {
    fun onMessage(msg: String)
}

object NativeEchoTcpServer {
    init {
        System.loadLibrary("local-tcp")
    }

    private var callbackRef: WeakReference<EchoMsgCallback>? = null;

    fun registerEchoMsgCallback(callback: EchoMsgCallback) {
        callbackRef = WeakReference(callback)
    }

    external fun echoServerStart(): String

    fun onEchoMessage(msg: String) {
        Log.d("src_test", "msg:" + msg)
        callbackRef?.get()?.onMessage(msg)
    }

}