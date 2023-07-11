package com.yr.springbootsecuritymybatiscrud.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密密码工具类
 */
public class PassWordEncoder {

    public static String getEncoderPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
