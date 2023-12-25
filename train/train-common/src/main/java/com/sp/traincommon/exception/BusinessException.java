package com.sp.traincommon.exception;

public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum exceptionEnum;

    public BusinessException(BusinessExceptionEnum exceptionEnum) {

        this.exceptionEnum = exceptionEnum;
    }

    public BusinessExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    /**
     * 避免递归打印很多异常
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
