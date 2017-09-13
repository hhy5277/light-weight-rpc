package com.qee.rpc.config.lbstrategy;

/**
 * Created by zhuqi on 2017/9/13.
 */
public interface LoadBalancedStrategy {

    /**
     * 从 0 -size-1 获取一个值
     *
     * @param size
     * @return
     */
    int strategy(int size);
}
