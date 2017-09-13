package com.qee.rpc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hzzhuqi1 on 2016/6/22.
 */

public class ExtractUtil {

    private static Logger log = LoggerFactory.getLogger(ExtractUtil.class);

    /**
     * 抽取目标List 的某一属性列的值作为一个新的List返回
     *
     * @param target
     * @param paramName
     * @param clazz
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<S> extractList(List<T> target, String paramName, Class<T> clazz) {
        List<S> result = new ArrayList<>();
        Field field = null;
        try {
            Field[] fields = clazz.getDeclaredFields();
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field tmpField : fields) {
                    if (tmpField.getName().equals(paramName)) {
                        field = tmpField;
                        field.setAccessible(true);
                        break;
                    }
                }
            }
            if (field == null) {
                for (Field tmpField : superFields) {
                    if (tmpField.getName().equals(paramName)) {
                        field = tmpField;
                        field.setAccessible(true);
                        break;
                    }
                }
            }
            if (target != null && target.size() > 0 && field != null) {
                Iterator<T> it = target.iterator();
                while (it.hasNext()) {
                    T obj = it.next();
                    S value = (S) field.get(obj);
                    if (value != null) {
                        result.add(value);
                    }
                }
            }
        } catch (Exception e) {
            log.error("ExtractUtil-extractList error", e);
        }
        return result;
    }
}