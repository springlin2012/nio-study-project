/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.ch01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 功能描述: 网络编程
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2018/03/12.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket conn = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        String s = br.readLine();
        while(s != null) {
            System.out.println(s);
            bw.write(s.toUpperCase() +"\n");
            bw.flush();

            s = br.readLine();
        }

        br.close();
        bw.close();
        conn.close();
    }

}
