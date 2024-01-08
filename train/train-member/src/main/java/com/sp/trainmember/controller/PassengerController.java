package com.sp.trainmember.controller;

import com.sp.traincommon.response.CommonResp;
import com.sp.trainmember.req.PassengerQueryReq;
import com.sp.trainmember.req.PassengerSaveReq;
import com.sp.trainmember.response.PassengerQueryResp;
import com.sp.trainmember.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/query-list")
    public CommonResp<List<PassengerQueryResp>> queryList(@Valid @RequestBody PassengerQueryReq passengerQueryReq) {
        List<PassengerQueryResp> passengerQueryResps = passengerService.queryList(passengerQueryReq);
        return new CommonResp<>(passengerQueryResps);
    }
}
