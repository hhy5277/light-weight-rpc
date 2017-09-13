package com.qee.rpc.threadpool;

import com.qee.rpc.config.lbstrategy.LoadBalancedStrategy;
import com.qee.rpc.config.model.MessageCallback;
import com.qee.rpc.config.model.ServiceAddressConfig;
import com.qee.rpc.utils.ServiceRemoteUrlContext;
import org.apache.commons.collections.CollectionUtils;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by zhuqi on 2017/9/12.
 */
public class CallBackExcuteHandler implements Callable<MessageCallback> {


    private String beanName;

    private List<InetSocketAddress> remoteAddresses;


    private LoadBalancedStrategy loadBalancedStrategy;


    public CallBackExcuteHandler(String beanName) {
        this.beanName = beanName;
    }

    public CallBackExcuteHandler(String beanName, List<InetSocketAddress> remoteAddresses) {
        this.beanName = beanName;
        this.remoteAddresses = remoteAddresses;
    }

    public CallBackExcuteHandler(String beanName, List<InetSocketAddress> remoteAddresses, LoadBalancedStrategy loadBalancedStrategy) {
        this.beanName = beanName;
        this.remoteAddresses = remoteAddresses;
        this.loadBalancedStrategy = loadBalancedStrategy;
    }

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
        if (CollectionUtils.isEmpty(remoteAddresses)) {
            List<ServiceAddressConfig> remoteUrls = ServiceRemoteUrlContext.getInstance().getRemoteUrls(beanName);
            if (CollectionUtils.isEmpty(remoteUrls)) {
                throw new RuntimeException("服务 [" + beanName + " ]远程地址错误");
            }
        }

        int size = remoteAddresses.size();

        int idx = loadBalancedStrategy.strategy(size);


        InetSocketAddress inetSocketAddress = remoteAddresses.get(idx);
        System.out.println("返回的地址" + inetSocketAddress + "  idx=" + idx);

        MessageCallback messageCallback = new MessageCallback();

        return messageCallback;
    }
}
