package com.eric.dao.impl;

import com.eric.dao.UserDao;
import com.eric.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 22:42
 */
public class UserDaoImpl implements UserDao {
    private String name;
    private int age;
    private List<String> list;
    private Map<String, User> userMap;
    private Properties properties;

    public List<String> getList()
    {
        return list;
    }

    public void setList(List<String> list)
    {
        this.list = list;
    }

    public Map<String, User> getUserMap()
    {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap)
    {
        this.userMap = userMap;
    }

    public Properties getProperties()
    {
        return properties;
    }

    public void setProperties(Properties properties)
    {
        this.properties = properties;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "UserDaoImpl{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void save()
    {
        System.out.println(this);
        System.out.println(this.list);
        System.out.println(this.userMap);
        System.out.println(this.properties);
        System.out.println("saving user in dao...");
    }
}
