package com.sp.trainmember.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;

import cn.hutool.jwt.JWTUtil;
import com.sp.traincommon.exception.BusinessException;
import com.sp.traincommon.exception.BusinessExceptionEnum;
import com.sp.traincommon.util.JwtUtil;
import com.sp.traincommon.util.SnowUtil;
import com.sp.trainmember.domain.Member;
import com.sp.trainmember.domain.MemberExample;
import com.sp.trainmember.mapper.MemberMapper;
import com.sp.trainmember.req.MemberLoginReq;
import com.sp.trainmember.req.MemberRegisterReq;
import com.sp.trainmember.req.MemberSendCodeReq;
import com.sp.trainmember.response.MemberLoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MemberService {
    private static Logger LOG = LoggerFactory.getLogger(MemberService.class);
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
        //使用雪花算法生成递增用户id
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        mapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq sendCodeReq) {
        String mobile = sendCodeReq.getMobile();
        //先判断手机号是否已经注册
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = mapper.selectByExample(memberExample);
        //如果手机号不存在，则插入一条记录
        //这里如果只是这样，会存在问题，如果某人发送验证码之后没有输入code，或者使用别人的手机号来发送验证码，那么
        //其他人就相当于被迫注册了
        if(!CollUtil.isNotEmpty(members)) {
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            mapper.insert(member);
        } else {
            LOG.info("手机号存在，不插入记录");
        }
        //生成验证码
        String code = RandomUtil.randomString(4);
        code = "8888";
        LOG.info("生成短信验证码",code);

        //保存短信记录表，手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间

        //对接短信通道，发送短信



    }

    public MemberLoginResp login(MemberLoginReq memberLoginReq) {
        String mobile = memberLoginReq.getMobile();
        //先判断手机号是否已经注册
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = mapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(members)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }
        if(memberLoginReq.getCode()==null || memberLoginReq.getCode().equals("")) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_EMPTY);
        }
        //校验短信验证码
        if(!"8888".equals(memberLoginReq.getCode())) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        MemberLoginResp memberLoginResp = new MemberLoginResp();
        memberLoginResp.setId(members.get(0).getId());
        memberLoginResp.setMobile(members.get(0).getMobile());
        //设置token

        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;

    }

}
