package com.ld.user.service.impl;

import com.ld.user.dataobject.UserInfo;
import com.ld.user.repository.UserInfoRespitory;
import com.ld.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoImplService implements UserInfoService {
    @Autowired
    private UserInfoRespitory userInfoRespitory;

    @Override
    public UserInfo findByOpenId(String openid) {
        return this.userInfoRespitory.findByOpenid(openid);
    }
}
