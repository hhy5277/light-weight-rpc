package com.qee.rpc.config.support;

import com.qee.rpc.config.parser.LightWeightRpcBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by zhuqi on 2017/9/12.
 */
@Component
public class LightWeightRpcNamespaceHandlerSupport extends NamespaceHandlerSupport {

    @Override
    public void init() {
        //注册用于解析<rpc>的解析器
        registerBeanDefinitionParser("provider", new LightWeightRpcBeanDefinitionParser());
        registerBeanDefinitionParser("cumsumer", new LightWeightRpcBeanDefinitionParser());
    }
}
