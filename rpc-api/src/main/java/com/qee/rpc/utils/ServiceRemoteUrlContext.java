package com.qee.rpc.utils;

import com.qee.rpc.config.model.ServiceAddressConfig;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuqi on 2017/9/13.
 */
public class ServiceRemoteUrlContext {

    private Map<String, List<ServiceAddressConfig>> remoteUrls;

    private volatile static ServiceRemoteUrlContext context;


    private ServiceRemoteUrlContext() {

    }

    public static ServiceRemoteUrlContext getInstance() {
        if (context == null) {
            synchronized (ServiceRemoteUrlContext.class) {
                if (context == null) {
                    context = new ServiceRemoteUrlContext();
                    context.remoteUrls = new HashMap<>();
                }
            }
        }
        return context;
    }


    /**
     * 添加一个远程地址，地址从service-url.properties 获取
     *
     * @param beanName
     * @param serviceAddressConfig
     * @return
     */
    public boolean addServiceAddress(String beanName, ServiceAddressConfig serviceAddressConfig) {
        if (StringUtils.isEmpty(beanName) || serviceAddressConfig == null) {
            return false;
        }
        synchronized (remoteUrls) {
            if (remoteUrls.get(beanName) == null) {
                List<ServiceAddressConfig> remoteAddress = new ArrayList<>();
                remoteAddress.add(serviceAddressConfig);
                remoteUrls.put(beanName, remoteAddress);
            } else {
                List<ServiceAddressConfig> serviceAddressConfigs = remoteUrls.get(beanName);
                if (serviceAddressConfigs.contains(serviceAddressConfig)) {
                    return false;
                }
                serviceAddressConfigs.add(serviceAddressConfig);
                return true;
            }
        }
        return false;
    }

    /**
     * 获取一个服务的远程地址 ，beanName like "com.qee.rpc.config.test.HelloService"
     *
     * @param beanName
     * @return
     */
    public List<ServiceAddressConfig> getRemoteUrls(String beanName) {
        return remoteUrls.get(beanName);
    }


}
