/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.ch02;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 功能描述: Channel
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2018/03/12.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class Server {

    public static void main(String[] args) {
        ServerSocketChannel ssc = null;
        SocketChannel socketChannel = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            socketChannel = ssc.accept();
            socketChannel.configureBlocking(false);

            ByteBuffer readBuffer = ByteBuffer.allocate(128);
            ByteBuffer writeBuffer = ByteBuffer.allocate(128);

            while (true) {
                socketChannel.read(readBuffer);
                readBuffer.flip();
                while (readBuffer.hasRemaining()) {
                    System.out.println((char) readBuffer.get());
                }

                // 输出入流至客户端
                writeBuffer.clear();
                writeBuffer.put("hello client.".getBytes());
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
            }

            /*
            socketChannel.close();
            ssc.close();
            */
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
