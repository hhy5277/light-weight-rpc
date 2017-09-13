package com.qee.rpc.threadpool;

import com.qee.rpc.config.model.MessageCallback;

import java.util.concurrent.Callable;

/**
 * Created by zhuqi on 2017/9/12.
 */
public class CallBackExcuteHandler implements Callable<MessageCallback> {


    public CallBackExcuteHandler() {
    }

    /**
     * 线程执行
     *
     * @return
     * @throws Exception
     */
    @Override
    public MessageCallback call() throws Exception {
        MessageCallback messageCallback = new MessageCallback();

        return messageCallback;
    }
}
