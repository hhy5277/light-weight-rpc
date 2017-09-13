package com.qee.rpc.invoke;

import com.qee.rpc.config.model.MessageCallback;
import com.qee.rpc.threadpool.CallBackExcuteHandler;
import com.qee.rpc.threadpool.ExcuteManager;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * Created by zhuqi on 2017/9/12.
 */
@Data
public class InterfaceProxyHandler implements InvocationHandler {


    private CallBackExcuteHandler excuteHandler;


    public InterfaceProxyHandler(CallBackExcuteHandler excuteHandler) {
        this.excuteHandler = excuteHandler;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        MessageCallback callback = ExcuteManager.invoke(excuteHandler);
        if (callback != null && callback.getResponse() != null) {
            return callback.getResponse();
        }
        return args[0];
    }


}
