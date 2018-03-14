/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.ch01;

import java.io.*;
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
public class Client {

    public static void main(String[] args) throws IOException {
        Socket conn = new Socket("127.0.0.1", 8080);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write("hello\n");
        bw.flush();
        String s = br.readLine();
        System.out.println(s);

        bw.write("world\n");
        bw.flush();
        s = br.readLine();
        System.out.println(s);

        br.close();
        bw.close();
        conn.close();
    }
}
