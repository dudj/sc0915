package com.ld.user.controller;

import com.ld.user.VO.ResultVO;
import com.ld.user.constant.CookieConstant;
import com.ld.user.constant.RedisConstant;
import com.ld.user.dataobject.UserInfo;
import com.ld.user.enums.ResultEnum;
import com.ld.user.enums.RoleEnum;
import com.ld.user.exception.UserInfoException;
import com.ld.user.service.UserInfoService;
import com.ld.user.utils.CookieUtil;
import com.ld.user.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/userinfo")
@Slf4j
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    /**
     * 买家端登录 将用户信息缓存在cookie中 openid = aaa
     */
    public ResultVO buyer(@RequestParam("openid") String openid,
        HttpServletResponse response){
        ResultVO resultVO = new ResultVO();
        log.warn("当前时间为:{},",new Date());
        log.info("买家端登录开始,openid为：{}",openid);
        try{
            //1.从数据库匹配数据
            UserInfo user = this.userInfoService.findByOpenId(openid);
            if(null != user){
                //2.判断角色
                if(RoleEnum.BUYER.getCode() != user.getRole()){
                    return ResultUtil.error(ResultEnum.LOGIN_ROLE_ERROR);
                }
                //3.设置cookie
                CookieUtil.set(response, CookieConstant.openid, user.getOpenid(),CookieConstant.expire);
            }else{
                return ResultUtil.error(ResultEnum.LOGIN_FAIL);
            }
        }catch (Exception e){
            log.error("买家登录异常消息:{}",e.getMessage());
            return ResultUtil.error(ResultEnum.LOGIN_FAIL);
        }
        log.info("买家端登录结束");
        return ResultUtil.success();
    }
    /**
     * 卖家端登录 cookie里设置token=UUID，redis设置key=UUID，vaule=openid
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletRequest request,
                           HttpServletResponse response){
        log.info("卖家端登录开始,openid为：{}",openid);
        Integer expire = CookieConstant.expire;
        try{
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            //判断是否在线
            if(null != cookie && openid.equals(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))){
                return ResultUtil.success();
            }
            UserInfo user = this.userInfoService.findByOpenId(openid);
            //1.数据库查询是否存在信息
            if(null == user){
                return ResultUtil.error(ResultEnum.LOGIN_FAIL);
            }
            //2.判断是否为卖家端
            if(RoleEnum.SELLER.getCode() != user.getRole()){
                return ResultUtil.error(ResultEnum.LOGIN_ROLE_ERROR);
            }
            //3.给cookie设置token
            String token = UUID.randomUUID().toString();
            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
            //4.将token的值 作为键 设置到redis中
            stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), openid, expire, TimeUnit.SECONDS);
        }catch (Exception e){
            log.error("卖家登录异常消息:{}",e.getMessage());
            return ResultUtil.error(ResultEnum.LOGIN_FAIL);
        }
        log.info("卖家端登录结束");
        return ResultUtil.success();
    }
}
