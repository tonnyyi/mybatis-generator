package com.tonny.codemachine.support;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author <a href=mailto:ktyi@iflytek.com>伊开堂</a>
 * @date 2019/2/28 16:08
 */
public class BeanUtils {
    /**
     * 设置属性
     */
    public static void setProperty(final Object target, final String propName, final Object propValue)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(propName);
        field.setAccessible(true);
        field.set(target, propValue);
    }

    /**
     * 获取属性
     */
    public static Object getProperty(final Object source, final String propName) throws NoSuchFieldException, IllegalAccessException {
        Field field = source.getClass().getDeclaredField(propName);
        field.setAccessible(true);
        return field.get(source);
    }

    /**
     * 执行无参方法
     */
    public static Object invoke(final Object targetBean, Class<?> targetClass, final String methodName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = targetClass.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(targetBean);
    }

}
