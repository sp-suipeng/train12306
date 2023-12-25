package com.sp.trainmember.service;

import cn.hutool.core.collection.CollUtil;
import com.sp.trainmember.domain.Member;
import com.sp.trainmember.domain.MemberExample;
import com.sp.trainmember.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMapper mapper;

    public int count() {
        return mapper.count();
    }


    public long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = mapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(members)) {
            throw new RuntimeException("用户已注册");
        }


        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);

        mapper.insert(member);

        return member.getId();
    }

}
