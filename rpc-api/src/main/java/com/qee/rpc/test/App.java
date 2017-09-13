package com.qee.rpc.test;

import com.qee.rpc.config.test.Invoker;
import com.qee.rpc.threadpool.ExcuteManager;
import com.qee.rpc.utils.ApplicationContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zhuqi on 2017/9/12.
 */
@ComponentScan(basePackages = "com.qee.rpc")
@EnableAutoConfiguration
public class App {

   /* public static void main(String[] args) {

        try {
            SpringApplication.run(App.class, args);
            System.out.println("the main Thread :" + Thread.currentThread().getName());

            Invoker invoker = (Invoker) ApplicationContextUtils.getBean("invoker");
            invoker.print();
        } finally {
            ExcuteManager.shutdownNow();
        }

    }*/

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

}
