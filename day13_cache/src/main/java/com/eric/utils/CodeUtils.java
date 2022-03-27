package com.eric.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 19:36
 */
@Slf4j
@Component
public class CodeUtils {

    public static final String[] PATCH = {"00000", "0000", "000", "00", "0", ""};

    public String generator(String number)
    {
        int salt = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        long result = number.hashCode() ^ salt ^ System.currentTimeMillis();
        String code = String.valueOf(Math.abs(result % 1000000));
        return PATCH[code.length() - 1] + code;
    }

    @Cacheable(value = "simCode", key = "#number")
    public String get(String number)
    {
        return null;
    }

    /*public static void main(String[] args)
    {
        System.out.println(patch("0"));
        System.out.println(patch("5"));
        System.out.println(patch("11"));
        System.out.println(patch("213"));
        System.out.println(patch("5555"));
        System.out.println(patch("32132"));
        System.out.println(patch("100000"));
    }

    static String patch(String code)
    {
        return PATCH[code.length() - 1] + code;
    }*/
}
