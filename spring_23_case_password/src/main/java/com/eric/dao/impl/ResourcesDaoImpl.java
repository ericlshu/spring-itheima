package com.eric.dao.impl;

import com.eric.dao.ResourcesDao;
import org.springframework.stereotype.Repository;

@Repository
public class ResourcesDaoImpl implements ResourcesDao
{
    public boolean readResources(String url, String password)
    {
        //模拟校验
        return password.equals("root");
    }
}
