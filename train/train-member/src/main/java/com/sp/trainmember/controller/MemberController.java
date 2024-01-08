package com.sp.trainmember.controller;

import com.sp.traincommon.response.CommonResp;
import com.sp.trainmember.req.MemberLoginReq;
import com.sp.trainmember.req.MemberRegisterReq;
import com.sp.trainmember.req.MemberSendCodeReq;
import com.sp.trainmember.response.MemberLoginResp;
import com.sp.trainmember.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    public CommonResp<Long> register(@Valid @RequestBody MemberRegisterReq mobileReq) {
        long register = memberService.register(mobileReq);
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(register);
        return commonResp;
    }

    /**
     * 发送短信验证码接口
     * @param mobileReq
     * @return
     */
    @PostMapping("/sendcode")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq mobileReq) {
        memberService.sendCode(mobileReq);
        CommonResp<Long> commonResp = new CommonResp<>();
        return commonResp;
    }

    /**
     * 登录验证码接口
     * @param mobileReq
     * @return
     */
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq mobileReq) {
        MemberLoginResp login = memberService.login(mobileReq);
        CommonResp<MemberLoginResp> commonResp = new CommonResp<>();
        commonResp.setContent(login);
        return commonResp;
    }
}
