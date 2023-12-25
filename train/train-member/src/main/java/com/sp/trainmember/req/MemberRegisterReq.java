package com.sp.trainmember.req;

/**
 * 封装的注册请求
 */
public class MemberRegisterReq {
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
