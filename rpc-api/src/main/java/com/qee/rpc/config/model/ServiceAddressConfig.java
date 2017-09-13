package com.qee.rpc.config.model;

import lombok.Data;

import java.net.InetSocketAddress;

/**
 * Created by zhuqi on 2017/9/13.
 */

@Data
public class ServiceAddressConfig {

    /**
     * 服务 全类名
     */
    private String beanName;


    /**
     * 远程主机
     */
    private String hostName;

    /**
     * 远程端口
     */
    private int remotePort;

    private InetSocketAddress remoteAddress;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ServiceAddressConfig that = (ServiceAddressConfig) object;

        if (remotePort != that.remotePort) return false;
        if (beanName != null ? !beanName.equals(that.beanName) : that.beanName != null) return false;
        return hostName != null ? hostName.equals(that.hostName) : that.hostName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (beanName != null ? beanName.hashCode() : 0);
        result = 31 * result + (hostName != null ? hostName.hashCode() : 0);
        result = 31 * result + remotePort;
        return result;
    }
}
