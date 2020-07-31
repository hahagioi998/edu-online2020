package com.qiyu.ucenterservice.service;

import com.qiyu.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiyu.ucenterservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author qiyu
 * @since 2020-07-20
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //注册
    void register(RegisterVo registerVo);
    //登录
    String login(UcenterMember member);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
