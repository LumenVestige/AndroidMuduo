package com.sanyinchen.nativelocaltcp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import com.sanyinchen.lib.EchoMsgCallback
import com.sanyinchen.lib.NativeEchoTcpServer
import com.sanyinchen.nativelocaltcp.test.EchoClient

class MainActivity : Activity() {
    private lateinit var text: TextView
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        text = findViewById<TextView>(R.id.echo_text)
        findViewById<Button>(R.id.echo_server).setOnClickListener {
            Thread {
                appendLogTxt("echoServerStarted")
                NativeEchoTcpServer.registerEchoMsgCallback(object : EchoMsgCallback {
                    override fun onMessage(msg: String) {
                        appendLogTxt(
                            "message from native echo server:$msg"
                                    + "\n--------------------------------------------------------"
                        )
                    }
                })
                NativeEchoTcpServer.echoServerStart()
            }.start()
        }
        findViewById<Button>(R.id.echo_client).setOnClickListener {
            Thread {
                appendLogTxt("create new client connected and will send 【10】  test msg....\n")
                var i = 0
                EchoClient.startTcpEchoClient {
                    i++
                    val msg =
                        "Hello,sync my time:" + System.currentTimeMillis() + " in " + i + " times"
                    appendLogTxt("client send:" + msg)
                    msg
                }
            }.start()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun appendLogTxt(msg: String) {
        handler.post {
            text.text = (text.text.toString() + "\n") + msg
            findViewById<ScrollView>(R.id.scrollContent).fullScroll(ScrollView.FOCUS_DOWN)
        }
    }
}