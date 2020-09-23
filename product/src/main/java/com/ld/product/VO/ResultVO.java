package com.ld.product.VO;

import lombok.Data;

/**
 * http 请求返回内容
 * @param <T>
 */
@Data
public class ResultVO<T> {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
}
