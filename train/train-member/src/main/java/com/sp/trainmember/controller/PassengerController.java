package com.sp.trainmember.controller;

import com.sp.traincommon.response.CommonResp;
import com.sp.trainmember.req.PassengerSaveReq;
import com.sp.trainmember.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
        System.out.println(req);
        passengerService.save(req);
        return new CommonResp<>();
    }
}
