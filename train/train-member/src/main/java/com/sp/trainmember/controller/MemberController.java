package com.sp.trainmember.controller;

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
    public Integer count() {
        return memberService.count();
    }

    @PostMapping("/register")
    public long register(String mobile) {

        return memberService.register(mobile);
    }
}
