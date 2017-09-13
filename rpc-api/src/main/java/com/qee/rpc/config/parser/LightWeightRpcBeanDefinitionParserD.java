package com.qee.rpc.config.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by zhuqi on 2017/9/13.
 */
public class LightWeightRpcBeanDefinitionParserD implements BeanDefinitionParser {

    private Class<?> beanClass;

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(beanClass);
        rootBeanDefinition.setLazyInit(false);

        String interfaces = element.getAttribute("interface");
        String clazz = element.getAttribute("class");
        String id = element.getAttribute("id");


        return null;
    }
}
