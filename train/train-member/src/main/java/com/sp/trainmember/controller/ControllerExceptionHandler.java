package com.sp.trainmember.controller;

import com.sp.traincommon.exception.BusinessException;
import com.sp.traincommon.response.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG =  LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 校验异常统一处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BindException e) {
        CommonResp commonResp = new CommonResp();
        LOG.error("校验异常:",e);
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }

    /**
     * 业务异常统一处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BusinessException e) {
        CommonResp commonResp = new CommonResp();
        LOG.error("业务异常:",e);
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getExceptionEnum().getDesc());
        return commonResp;
    }

    /**
     * 所有异常统一处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp exceptionHandler(Exception e) {
        CommonResp commonResp = new CommonResp();
        LOG.error("系统异常:",e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统出现异常，请联系管理员");
        return commonResp;
    }


}
