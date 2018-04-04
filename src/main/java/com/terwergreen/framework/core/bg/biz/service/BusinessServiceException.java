package com.terwergreen.framework.core.bg.biz.service;

public class BusinessServiceException extends Exception {

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