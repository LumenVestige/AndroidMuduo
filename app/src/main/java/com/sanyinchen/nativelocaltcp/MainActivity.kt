package com.sanyinchen.nativelocaltcp

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.sanyinchen.lib.NativeLocalTcpServer
import com.sanyinchen.nativelocaltcp.test.EchoClient

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.echo_server).setOnClickListener {
            Thread {

                NativeLocalTcpServer.echoServerStart()
            }.start()
        }
        findViewById<Button>(R.id.echo_client).setOnClickListener {
            Thread {
                EchoClient.connect()
            }.start()
        }
    }
}