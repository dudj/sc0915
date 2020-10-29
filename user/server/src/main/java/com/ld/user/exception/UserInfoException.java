package com.ld.user.exception;

import com.ld.user.enums.ResultEnum;

public class UserInfoException extends RuntimeException{
    private Integer code;
    public UserInfoException(Integer code, String message){
        super(message);
        this.code = code;
    }
    public UserInfoException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
