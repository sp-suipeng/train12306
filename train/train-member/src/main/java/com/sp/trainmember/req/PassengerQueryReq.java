package com.sp.trainmember.req;

import jakarta.validation.constraints.NotNull;

public class PassengerQueryReq {
    @NotNull(message = "【会员ID】不能为空")
    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


    @Override
    public String toString() {
        return "PassengerQueryReq{" +
                "memberId=" + memberId +
                '}';
    }
}
