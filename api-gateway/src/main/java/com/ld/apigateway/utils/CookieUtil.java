package com.ld.apigateway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置cookie值得工具类
 */
public class CookieUtil {
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }
    public static Cookie get(HttpServletRequest request,
                             String name){
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Cookie cookie:cookies){
                if(cookie.getName() == name){
                    return cookie;
                }
            }
        }
        return null;
    }
}
