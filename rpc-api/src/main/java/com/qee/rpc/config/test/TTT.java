package com.qee.rpc.config.test;


import com.qee.rpc.invoke.InterfaceProxyHandler;
import com.qee.rpc.threadpool.CallBackExcuteHandler;
import com.qee.rpc.threadpool.ExcuteManager;

import java.lang.reflect.Proxy;

/**
 * Created by zhuqi on 2017/9/12.
 */
public class TTT {
    public static void main(String[] args) {
        try {
            //////////////////


            HelloService helloService = getHelloService();
            String hello = helloService.hello(null);

            System.out.println("dddddd");


        } catch (Exception e) {
            System.out.println(e);

        }finally {
            ExcuteManager.shutdownNow();
        }

    }

    public static HelloService getHelloService() {
        CallBackExcuteHandler callBackExcuteHandler = new CallBackExcuteHandler();
        InterfaceProxyHandler interfaceProxyHandler = new InterfaceProxyHandler(callBackExcuteHandler);
        return (HelloService) Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class[]{HelloService.class}, interfaceProxyHandler);
    }
}
