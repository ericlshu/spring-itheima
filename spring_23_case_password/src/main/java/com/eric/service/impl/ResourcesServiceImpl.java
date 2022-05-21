package com.eric.service.impl;

import com.eric.dao.ResourcesDao;
import com.eric.service.ResourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResourcesServiceImpl implements ResourcesService
{
    @Resource
    private ResourcesDao resourcesDao;

    public boolean openURL(String url, String password)
    {
        System.out.println("password.length() = " + password.length());
        return resourcesDao.readResources(url, password);
    }
}
