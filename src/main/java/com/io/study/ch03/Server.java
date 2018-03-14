/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.ch03;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 功能描述: Channel Scatter I/O
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2018/03/12.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            SocketChannel socketChannel = ssc.accept();

            ByteBuffer readBuffer = ByteBuffer.allocate(128);
            ByteBuffer buffer2 = ByteBuffer.allocate(16);
            ByteBuffer[] buffers = {readBuffer, buffer2};

            long bytesRead = socketChannel.read(buffers);

            readBuffer.flip();
            buffer2.flip();
            while (readBuffer.hasRemaining()) {
                System.out.print((char) readBuffer.get());
            }
            while (buffer2.hasRemaining()) {
                System.out.print((char) buffer2.get());
            }

            buffer2.clear();

            socketChannel.close();
            ssc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
