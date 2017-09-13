package com.qee.rpc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhuqi on 2017/9/12.
 */
@Configuration
@ImportResource(value = {"classpath:test.xml"})
public class LightWeightRpcConfiguration {

}
