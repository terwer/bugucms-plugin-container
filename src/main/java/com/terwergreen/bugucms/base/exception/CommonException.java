package com.terwergreen.bugucms.base.exception;

/**
 * @Author Terwer
 * @Date 2018/11/16 14:44
 * @Version 1.0
 * @Description 通用异常类
 **/
public class CommonException extends Exception {
    /**
     * 默认异常信息
     *
     * @param paramThrowable 堆栈信息
     */
    public CommonException(Throwable paramThrowable) {
        super(paramThrowable);
    }

    /**
     * 自定义异常
     *
     * @param paramString 自定义异常信息
     */
    public CommonException(String paramString) {
        super(paramString);
    }

    /**
     * 自定义异常
     *
     * @param paramString    自定义异常信息
     * @param paramThrowable 堆栈信息
     */
    public CommonException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

}