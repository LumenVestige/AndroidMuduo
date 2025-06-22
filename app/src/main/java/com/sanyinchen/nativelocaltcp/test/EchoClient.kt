package com.sanyinchen.nativelocaltcp.test

import android.util.Log
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.Socket

object EchoClient {
    private val TAG = "EchoClient"

    fun startTcpEchoClient(getSendMsgAction: () -> String) {
        val host = "127.0.0.1"
        val port = 2007
        val socket = Socket()
        try {
            Log.d(TAG, "Connecting to $host:$port...")
            socket.connect(InetSocketAddress(host, port), 5000)

            val out =
                PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
            var i = 0;
            while (i < 10) {
                i++;
                val message = getSendMsgAction()
                Log.d(TAG, "Sending: $message")
                out.println(message)
                Thread.sleep(1000)
            }
            socket.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error in TCP client: ${e.message}", e)
        }
    }
}