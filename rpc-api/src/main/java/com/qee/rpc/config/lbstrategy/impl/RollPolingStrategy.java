package com.qee.rpc.config.lbstrategy.impl;

import com.qee.rpc.config.lbstrategy.LoadBalancedStrategy;

/**
 * Created by zhuqi on 2017/9/13.
 */
public class RollPolingStrategy implements LoadBalancedStrategy {

    private int currentValue = 0;

    private Class<?> clazz;

    public RollPolingStrategy(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int strategy(int size) {
        synchronized (clazz) {
            int nextValue = (currentValue + 1) % size;
            currentValue = nextValue;
            if (currentValue > size) {
                nextValue = 0;
            }
            return currentValue;
        }
    }
}
