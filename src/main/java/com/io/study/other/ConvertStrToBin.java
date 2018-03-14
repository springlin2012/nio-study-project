/*
 * Copyright (c) 2017 xiaoniu, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.io.study.other;

/**
 * 功能描述: 字符转换二进制
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2018/03/14.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class ConvertStrToBin {

    public static void main(String[] args) {
        String str = "hello World";

        char[] chars = str.toCharArray();

        for (int i=0; i< chars.length; i++) {
            System.out.println("字符--二进制");
            System.out.println(chars[i]+"\t"+Integer.toBinaryString(chars[i]));
        }
    }

}
