package com.sp.trainmemer.controller;

import com.sp.trainmemer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/count")
    public Integer count() {
        return memberService.count();
    }
}
