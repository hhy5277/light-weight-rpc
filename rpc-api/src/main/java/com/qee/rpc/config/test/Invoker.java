package com.qee.rpc.config.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhuqi on 2017/9/13.
 */
@Component
public class Invoker {

    @Autowired
    private HelloService helloService;

    @Resource(name = "helloService")
    private HelloService helloService2;

    public void print() {
        System.out.println(helloService.hello("123"));
        System.out.println(helloService2.hello("122344"));
    }
}
