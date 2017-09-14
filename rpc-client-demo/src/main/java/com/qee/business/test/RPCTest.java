package com.qee.business.test;

import com.qee.rpc.config.model.LightWeightRPCElement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhuqi on 2017/9/12.
 */
public class RPCTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");
        LightWeightRPCElement p1= (LightWeightRPCElement)ctx.getBean("helloServiceImplConfig");
        LightWeightRPCElement p2= (LightWeightRPCElement)ctx.getBean("helloServiceConfig");
        System.out.println(p1);
        System.out.println(p2);

    }
}
