package com.terwergreen.bugucms.base.service;

/**
 * @Author Terwer
 * @Date 2018/3/6 14:44
 * @Version 1.0
 * @Description 业务逻辑处理通用异常类
 **/
public class BusinessServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessServiceException() {
    }

    public BusinessServiceException(Throwable paramThrowable) {
        super(paramThrowable);
    }

    public BusinessServiceException(String paramString) {
        super(paramString);
    }

    public BusinessServiceException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

}