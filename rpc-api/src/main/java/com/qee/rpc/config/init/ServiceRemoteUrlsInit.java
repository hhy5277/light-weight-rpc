package com.qee.rpc.config.init;

import com.qee.rpc.config.model.ServiceAddressConfig;
import com.qee.rpc.utils.ServiceRemoteUrlContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by zhuqi on 2017/9/13.
 */
@Component
public class ServiceRemoteUrlsInit implements InitializingBean {

    /**
     * 远程服务配置地址路径，默认
     */
    @Value("${remote-urls-path:classpath:service-urls.properties}")
    private String remoteUrlsPropertyPath;

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties pps = new Properties();
        if (!remoteUrlsPropertyPath.startsWith("classpath")) {
            throw new RuntimeException(remoteUrlsPropertyPath + "不存在");
        }
        String[] filePath = remoteUrlsPropertyPath.split(":");
        if (filePath == null || filePath.length != 2) {
            throw new RuntimeException(remoteUrlsPropertyPath + "内容配置错误");
        }
        ClassPathResource resource = new ClassPathResource(filePath[1]);
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
                    throw new RuntimeException(remoteUrlsPropertyPath + " 配置内容错误");
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
