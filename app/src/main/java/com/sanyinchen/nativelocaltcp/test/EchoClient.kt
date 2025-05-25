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

    fun connect() {
        startTcpEchoClient("127.0.0.1", 2007)
    }

    private fun startTcpEchoClient(host: String, port: Int) {
        val socket = Socket()
        try {
            Log.d(TAG, "Connecting to $host:$port...")
            socket.connect(InetSocketAddress(host, port), 5000)

            val out =
                PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
            val input = BufferedReader(InputStreamReader(socket.getInputStream()))

            val message = "Hello from Android Kotlin!"
            Log.d(TAG, "Sending: $message")
            out.println(message)

            val response = input.readLine()
            Log.d(TAG, "Received: $response")

            socket.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error in TCP client: ${e.message}", e)
        }
    }
}