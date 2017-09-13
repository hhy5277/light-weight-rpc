package com.qee.rpc.config.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhuqi on 2017/9/13.
 */
public class CC {
    public static volatile AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println(integer.getAndIncrement());
        System.out.println(integer.getAndIncrement());

    }
}
