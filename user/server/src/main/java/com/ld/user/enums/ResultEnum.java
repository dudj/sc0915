package com.ld.user.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    LOGIN_FAIL(0,"登录失败"),
    LOGIN_ROLE_ERROR(-1,"角色有误");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
