package com.eric.service.impl;

import com.eric.dao.LogDao;
import com.eric.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements LogService
{
    @Resource
    private LogDao logDao;

    public void log(String out, String in, Double money)
    {
        logDao.log("转账操作由" + out + "到" + in + ",金额：" + money);
    }
}
