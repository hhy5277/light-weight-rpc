package com.qee.rpc.config.parser;

import com.qee.rpc.config.model.LightWeightRPCElement;
import com.qee.rpc.threadpool.ProxyObjectManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by zhuqi on 2017/9/12.
 */
public class LightWeightRpcBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {


    protected Class getBeanClass(Element element) {
        return LightWeightRPCElement.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String interfaces = element.getAttribute("interface");
        String clazz = element.getAttribute("class");
        String id = element.getAttribute("id");
        bean.addPropertyValue("id", id + "Config");
        if (StringUtils.hasText(id)) {
            bean.addPropertyValue("beanName", id);
        }
        if (StringUtils.hasText(clazz)) {
            bean.addPropertyValue("clazz", clazz);
        }
        if (StringUtils.hasText(interfaces)) {
            bean.addPropertyValue("interfaces", interfaces);
        }
       /* ProxyObjectManager.addProxy(id, new ObjectFactory<Object>() {
            @Override
            public Object getObject() throws BeansException {
                return null;
            }
        });*/
    }

}
