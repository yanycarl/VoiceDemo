package com.guango.voicedemo;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sockethelper {

    private static Socket streamSocket = null;


    public static void buildClient() {

        @SuppressLint("CI_NotAllowInvokeExecutorsMethods")
        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(new Runnable() {
            @Override
            public void run() {

                if(streamSocket == null){
                    try {
                        streamSocket = new Socket(InetAddress.getByName("16p23b6752.51mypc.cn").getHostAddress(), 52051);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                byte[] inputBuffer = "#YanyCarl机$你好%efgegfjdcjsd".getBytes();
                OutputStream os = null;
                try {
                    os = streamSocket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedOutputStream writer = new BufferedOutputStream(os);
                try {
                    writer.write(inputBuffer, 0, inputBuffer.length);
                    Log.d("yanyao", String.valueOf(inputBuffer));
                    writer.flush();
//                    streamSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
