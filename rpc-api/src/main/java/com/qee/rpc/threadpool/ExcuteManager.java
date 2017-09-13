package com.qee.rpc.threadpool;

import com.qee.rpc.config.model.MessageCallback;

import java.util.concurrent.*;

/**
 * Created by zhuqi on 2017/9/12.
 */
public class ExcuteManager {

    /**
     * 默认是200个线程
     */
    private static final int DEFAULT_THRED_NUM = 200;

    /**
     * 超时时间为1秒
     */
    private static final int DEFAULT_TIME_OUT_TIME = 1000;

    private static ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_THRED_NUM);

    public static MessageCallback invoke(Callable<MessageCallback> call) {
        Future<MessageCallback> submit = executorService.submit(call);
        try {
            return submit.get(DEFAULT_TIME_OUT_TIME, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            submit.cancel(true);
            throw new RuntimeException("the method is interupted ", e);
        } catch (ExecutionException e) {
            submit.cancel(true);
            throw new RuntimeException("the method cal excute exception", e);
        } catch (TimeoutException e) {
            System.out.println(Thread.currentThread().getName());
            submit.cancel(true);
            throw new RuntimeException("the method call is time out  ", e);
        }
    }

    public static void shutdown() {
        executorService.shutdown();
    }

    public static void shutdownNow() {
        executorService.shutdownNow();
    }

}
