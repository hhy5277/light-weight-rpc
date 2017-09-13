package com.qee.rpc.threadpool;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuqi on 2017/9/12.
 */
public class ProxyObjectManager {

    private static Map<String, Object> proxy = new HashMap<>();

    public static void addProxy(String beanName, Object bean) {
        synchronized (proxy) {
            if (StringUtils.isEmpty(beanName) && bean != null) {
                proxy.put(beanName, bean);
            }

        }
    }

    public static Object getProxy(String beanName) {
        return proxy.get(beanName);
    }
}
