package com.qee.rpc.register;

import com.qee.rpc.config.model.LightWeightRPCElement;
import com.qee.rpc.invoke.InterfaceProxyHandler;
import com.qee.rpc.threadpool.CallBackExcuteHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Proxy;

/**
 * Created by zhuqi on 2017/9/12.
 */
//@Component
public class RegisterRpcProxyBeanProcessor implements BeanPostProcessor, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object target = bean;
        if (bean instanceof LightWeightRPCElement) {
            LightWeightRPCElement rpcElement = (LightWeightRPCElement) bean;
            Class<?> clazz = null;
            if (!StringUtils.isEmpty(rpcElement.getInterfaces())) {
                try {
                    clazz = Class.forName(rpcElement.getInterfaces());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("获取 [" + rpcElement.getInterfaces() + " ] class字节码失败");
                }
            }
            CallBackExcuteHandler callBackExcuteHandler = new CallBackExcuteHandler();
            InterfaceProxyHandler interfaceProxyHandler = new InterfaceProxyHandler(callBackExcuteHandler);
            target = Proxy.newProxyInstance(bean.getClass().getClassLoader(), new Class[]{clazz}, interfaceProxyHandler);
            if (beanFactory instanceof DefaultListableBeanFactory) {
                DefaultListableBeanFactory defaultFactory = (DefaultListableBeanFactory) beanFactory;
                defaultFactory.registerSingleton(rpcElement.getBeanName(), target);
            }
        }
        return target;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
