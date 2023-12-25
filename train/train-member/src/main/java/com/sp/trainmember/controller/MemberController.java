package com.sp.trainmember.controller;

import com.sp.traincommon.response.CommonResp;
import com.sp.trainmember.req.MemberRegisterReq;
import com.sp.trainmember.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/count")
    public CommonResp<Integer> count() {
        int count = memberService.count();
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }

    /**
     * 注册接口
     * @param mobileReq 注册手机号
     * @return 注册id
     */
    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq mobileReq) {
        long register = memberService.register(mobileReq);
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(register);
        return commonResp;
    }
}
