package com.sp.traincommon.exception;

public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已存在"),
    //验证码相关
    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码"),
    MEMBER_MOBILE_CODE_EMPTY("短信验证码不能为空"),
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误"),
    ;
    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}