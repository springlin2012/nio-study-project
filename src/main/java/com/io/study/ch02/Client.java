/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.ch02;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
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
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer writeBuffer = ByteBuffer.allocate(128);
            writeBuffer.put("hello world".getBytes());

            writeBuffer.flip();
            socketChannel.write(writeBuffer);

            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
