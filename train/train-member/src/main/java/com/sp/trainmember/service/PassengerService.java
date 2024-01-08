package com.sp.trainmember.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.sp.traincommon.context.LoginMemberContext;
import com.sp.traincommon.util.SnowUtil;
import com.sp.trainmember.domain.Passenger;
import com.sp.trainmember.domain.PassengerExample;
import com.sp.trainmember.mapper.PassengerMapper;
import com.sp.trainmember.req.PassengerQueryReq;
import com.sp.trainmember.req.PassengerSaveReq;
import com.sp.trainmember.response.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();

        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    public List<PassengerQueryResp> queryList(PassengerQueryReq passengerQueryReq) {
        PassengerExample example = new PassengerExample();
        PassengerExample.Criteria criteria = example.createCriteria();
        if(ObjectUtil.isNotNull(passengerQueryReq.getMemberId())) {
            criteria.andMemberIdEqualTo(passengerQueryReq.getMemberId());
        }

        List<Passenger> passengers = passengerMapper.selectByExample(example);
        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengers, PassengerQueryResp.class);
        return passengerQueryResps;

    }
}
