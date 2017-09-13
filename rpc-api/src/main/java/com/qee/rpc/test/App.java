package com.qee.rpc.test;

import com.qee.rpc.config.test.Invoker;
import com.qee.rpc.threadpool.ExcuteManager;
import com.qee.rpc.utils.ApplicationContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhuqi on 2017/9/12.
 */
@ComponentScan(basePackages = "com.qee.rpc")
@EnableAutoConfiguration
public class App {

    private static ExecutorService executorService = Executors.newCachedThreadPool();


    private static final CountDownLatch cd = new CountDownLatch(1);


    public static void main(String[] args) {

        try {
            SpringApplication.run(App.class, args);
            System.out.println("the main Thread :" + Thread.currentThread().getName());
            final Invoker invoker = (Invoker) ApplicationContextUtils.getBean("invoker");
            for (int i = 0; i < 300; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            cd.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        invoker.print();
                    }
                });
            }


            cd.countDown();

            Thread.sleep(100000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ExcuteManager.shutdown();
            executorService.shutdown();
        }

    }

    /*public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }*/

}
