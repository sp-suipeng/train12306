package com.sp.trainmember.service;

import cn.hutool.core.collection.CollUtil;
import com.sp.traincommon.exception.BusinessException;
import com.sp.traincommon.exception.BusinessExceptionEnum;
import com.sp.trainmember.domain.Member;
import com.sp.trainmember.domain.MemberExample;
import com.sp.trainmember.mapper.MemberMapper;
import com.sp.trainmember.req.MemberRegisterReq;
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

    /**
     * 注册函数
     * @param mobileReq 注册手机号
     * @return
     */
    public long register(MemberRegisterReq mobileReq) {
        String mobile = mobileReq.getMobile();
        //先判断手机号是否已经注册
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = mapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(members)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        //开始注册流程
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        mapper.insert(member);
        return member.getId();
    }

}
