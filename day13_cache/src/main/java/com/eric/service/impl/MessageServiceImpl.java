package com.eric.service.impl;

import com.eric.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 18:48
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final Map<String, String> cache = new HashMap<>();

    @Override
    public String getCode(String number)
    {
        String code = number.substring(number.length() - 6);
        cache.put(number, code);
        return code;
    }

    @Override
    public boolean check(String number, String code)
    {
        String queryCode = cache.get(number);
        return code.equals(queryCode);
    }
}
