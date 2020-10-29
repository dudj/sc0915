package com.ld.apigateway.filter;

import com.ld.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
/**
 * 权限拦截 区分买家和卖家
 */
public class AuthFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        /**
         * 创建订单 order/create 只能买家访问
         * 完结订单 order/finish 只能卖家访问
         * 产品列表 product/list 都允许访问
         */
        System.out.println("请求地址："+request.getRequestURI());
        /*if("/order/order/create".equals(request.getRequestURI())){
            Cookie cookie = CookieUtil.get(request, "openid");
            if(cookie == null || StringUtils.isEmpty(cookie.getValue())){
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        //验证 redis中是否有数据
        if("/order/order/finish".equals(request.getRequestURI())){
            Cookie cookie = CookieUtil.get(request, "token");
            if(cookie == null){
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
            String openid = stringRedisTemplate.opsForValue().get(String.format("token_%s", cookie.getValue()));
            if(StringUtils.isEmpty(openid)){
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }*/
        return null;
    }
}
