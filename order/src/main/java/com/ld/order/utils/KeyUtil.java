package com.ld.order.utils;


import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一id
     * key 时间戳+随机数
     * synchronized 避免多线程生成相同的数据
     */
    public static synchronized String generateUniqueKey(){
        Random random = new Random();
        int num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
