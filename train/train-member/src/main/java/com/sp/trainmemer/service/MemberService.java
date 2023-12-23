package com.sp.trainmemer.service;

import com.sp.trainmemer.mapper.MemberMapper;
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
