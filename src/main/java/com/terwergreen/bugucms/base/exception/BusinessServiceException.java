package com.terwergreen.bugucms.base.exception;

/**
 * @Author Terwer
 * @Date 2018/3/6 14:44
 * @Version 1.0
 * @Description 业务逻辑处理通用异常类
 **/
public class BusinessServiceException extends Exception {
    /**
     * 默认异常信息
     *
     * @param paramThrowable 堆栈信息
     */
    public BusinessServiceException(Throwable paramThrowable) {
        super(paramThrowable);
    }

    /**
     * 自定义异常
     *
     * @param paramString 自定义异常信息
     */
    public BusinessServiceException(String paramString) {
        super(paramString);
    }

    /**
     * 自定义异常
     *
     * @param paramString    自定义异常信息
     * @param paramThrowable 堆栈信息
     */
    public BusinessServiceException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

}