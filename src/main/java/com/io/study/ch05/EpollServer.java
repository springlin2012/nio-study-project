/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.ch05;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 功能描述: Selector/Epoll
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2018/03/13.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class EpollServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            // 注册 channel, 并且制定感兴趣的事件 accept (接受连接)
            // SelectionKey.OP_ACCEPT，这个事件代表的是有客户端发起TCP连接请求。
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writerBuff = ByteBuffer.allocate(128);
            writerBuff.put("received".getBytes());
            writerBuff.flip();

            while(true) {
                // 使用 select 方法阻塞住线程，
                // 当select 返回的时候，线程被唤醒。再通过selectedKeys方法得到所有可用channel的集合。
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                // 遍历这个集合，
                // 如果其中channel 上有连接到达，就接受新的连接，然后把这个新的连接也注册到selector中去。
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        // 创建新的连接，并且把连接注册到selector上，而且申明这个 channel 只对读操作感兴趣
                        SocketChannel socketCHannel = ssc.accept();
                        socketCHannel.configureBlocking(false);
                        socketCHannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        // 如果有channel是读，那就把数据读出来，并且把它感兴趣的事件改成写
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);

                        readBuff.flip();
                        System.out.println("received: "+ new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        // 如果是写，就把数据写出去，并且把感兴趣的事件改成读
                        writerBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writerBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}