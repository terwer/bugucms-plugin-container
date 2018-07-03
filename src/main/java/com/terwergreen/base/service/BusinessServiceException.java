package com.terwergreen.base.service;

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