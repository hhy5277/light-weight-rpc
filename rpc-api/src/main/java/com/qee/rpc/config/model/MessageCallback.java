package com.qee.rpc.config.model;


import lombok.Data;

/**
 * Created by zhuqi on 2017/9/12.
 */
@Data
public class MessageCallback {

    private RcpResponse response;

    private Object proxy;
}
