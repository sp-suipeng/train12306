package com.sp.trainmember.req;


import jakarta.validation.constraints.NotBlank;

/**
 * 封装的注册请求
 */
public class MemberRegisterReq {
    @NotBlank(message = "手机号不能为空！！！")
    private String mobile;

    public MemberRegisterReq(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
