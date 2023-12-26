package com.sp.trainmember.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberLoginReq {
    @NotBlank(message = "手机号不能为空！！！")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
    private String mobile;

    private String code;



    public MemberLoginReq(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MemberLoginReq{" +
                "mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
