package com.ld.user.service;

import com.ld.user.dataobject.UserInfo;

public interface UserInfoService {
    /**
     * 通过openid 获取用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenId(String openid);
}
