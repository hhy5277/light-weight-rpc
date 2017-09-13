package com.qee.rpc.config.model;

import lombok.Data;
import lombok.ToString;

/**
 * Created by zhuqi on 2017/9/12.
 */
@Data
@ToString
public class LightWeightRPCElement {

    private String id;

    private String beanName;

    private String clazz;

    private String interfaces;
}
