package com.ld.order.utils;

import com.ld.order.VO.ResultVO;
import com.ld.order.enums.ResultEnum;

public class ResultUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }
    public static ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMessage());
        return resultVO;
    }
}
