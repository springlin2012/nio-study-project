/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.demo.bio;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 功能描述: XXXX
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2018/06/23.
 * <p/>
 * Copyright (c) 凌霄阁工作室-版权所有
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            String currentTime = null;
            String body = null;
            while (true) {
               body = in.readLine();
                if (body == null) { // 读到输入流尾部时
                    break;
                }
                System.out.println("The time server receive order: "+ body);

                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString():"BAD ORDER";

                System.out.println("START.out print");
                out.print(currentTime); // TODO 未输出到客户端
                System.out.println("END.out print");
            }
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
                out = null;
            }

            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}