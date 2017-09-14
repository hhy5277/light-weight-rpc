package com.qee.rpc.config.init;

import com.qee.rpc.config.model.ServiceAddressConfig;
import com.qee.rpc.utils.ServiceRemoteUrlContext;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by zhuqi on 2017/9/13.
 */
public class ServiceRemoteUrlsInit {

    /**
     * 远程服务配置地址路径，默认
     *
     * @throws Exception
     */
    public void initRemoteUrls() throws Exception {
        Properties pps = new Properties();
        ClassPathResource resource = new ClassPathResource("service-urls.properties");
        if (resource == null) {
            throw new RuntimeException("服务提供远程文件service-urls.properties 不存在");
        }
        InputStream in = new BufferedInputStream(resource.getInputStream());
        pps.load(in);
        Enumeration en = pps.propertyNames();

        while (en.hasMoreElements()) {
            String beanName = (String) en.nextElement();
            String strRemoteUrls = pps.getProperty(beanName);
            String[] remoteUrls = strRemoteUrls.split(",");
            if (remoteUrls == null || remoteUrls.length == 0) {
                break;
            }
            for (String remoteUrl : remoteUrls) {
                String[] hostPort = remoteUrl.split(":");
                if (hostPort == null || hostPort.length != 2) {
                    throw new RuntimeException("service-urls.properties" + " 配置内容错误");
                }
                ServiceAddressConfig serviceAddressConfig = new ServiceAddressConfig();
                serviceAddressConfig.setBeanName(beanName);
                serviceAddressConfig.setHostName(hostPort[0]);
                serviceAddressConfig.setRemotePort(Integer.valueOf(hostPort[1]));
                InetSocketAddress socketAddress = new InetSocketAddress(serviceAddressConfig.getHostName(), serviceAddressConfig.getRemotePort());
                serviceAddressConfig.setRemoteAddress(socketAddress);
                ServiceRemoteUrlContext.getInstance().addServiceAddress(beanName, serviceAddressConfig);
            }

        }

    }
}
