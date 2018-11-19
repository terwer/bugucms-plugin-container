package com.terwergreen.bugucms.utils;

import com.terwergreen.bugucms.base.exception.CommonException;
import oracle.sql.CLOB;
import oracle.sql.NCLOB;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 获取到的List<Map>结果集转化为JavaBean工具类
 *
 * @author terwergreen
 * @date 2018-4-4
 */
public class BeanUtils {
    /**
     * 根据List<Map<String, Object>>数据转换为JavaBean数据
     *
     * @param datas
     * @param beanClass
     * @return
     * @throws CommonException
     */
    public static <T> T optionList2bean(List<Map<String, Object>> datas, Class<T> beanClass) throws CommonException {
        //要返回的实例
        T t = null;
        // 对象字段名称
        String fieldName = "";
        // 对象方法名称
        String methodName = "";
        // 对象方法需要赋的值
        Object methodSetValue = "";

        try {
            // 创建一个泛型类型实例
            t = beanClass.newInstance();
            // 得到对象所有字段
            Field fields[] = beanClass.getDeclaredFields();
            // 遍历数据
            for (Map<String, Object> map : datas) {
                // 遍历所有字段，对应配置好的字段并赋值
                for (Field field : fields) {
                    if (null != field) {
                        // 获取字段名称
                        fieldName = field.getName();
                        if (map.get("optionName").equals(fieldName)) {
                            // 获取data里的对应值
                            // 如果是CLOB类型，则需要手动转换
                            String dataType = map.get("optionValue").getClass().getTypeName();
                            if (CLOB.class.getTypeName().equals(dataType)) {
                                methodSetValue = OracleUtils.ClobToString((CLOB) map.get("optionValue"));
                            } else if (NCLOB.class.getTypeName().equals(dataType)) {
                                methodSetValue = OracleUtils.ClobToString((NCLOB) map.get("optionValue"));
                            } else {
                                methodSetValue = map.get("optionValue");
                            }
                            // 拼接set方法
                            methodName = "set" + StringUtils.capitalize(fieldName);
                            // 赋值给字段
                            Method m = beanClass.getDeclaredMethod(methodName, field.getType());
                            m.invoke(t, methodSetValue);
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            throw new CommonException("创建beanClass实例异常", e);
        } catch (IllegalAccessException e) {
            throw new CommonException("创建beanClass实例异常", e);
        } catch (SecurityException e) {
            throw new CommonException("获取[" + fieldName + "] getter setter 方法异常", e);
        } catch (NoSuchMethodException e) {
            throw new CommonException("获取[" + fieldName + "] getter setter 方法异常", e);
        } catch (IllegalArgumentException e) {
            throw new CommonException("[" + methodName + "] 方法赋值异常", e);
        } catch (InvocationTargetException e) {
            throw new CommonException("[" + methodName + "] 方法赋值异常", e);
        } catch (SQLException e) {
            throw new CommonException("[" + methodName + "] 方法赋值异常", e);
        } catch (IOException e) {
            throw new CommonException("[" + methodName + "] 方法赋值异常", e);
        }
        // 返回
        return t;
    }

}