package com.ld.user.repository;

import com.ld.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRespitory extends JpaRepository<UserInfo,String> {
    UserInfo findByOpenid(String openid);
}
