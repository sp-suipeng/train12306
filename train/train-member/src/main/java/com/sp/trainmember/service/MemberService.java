package com.sp.trainmember.service;

import com.sp.trainmember.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberMapper mapper;

    public int count() {
        return mapper.count();
    }

}
