package com.eric.domain;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-08 19:38
 */
public class VO {
    public List<User> userList;

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    @Override
    public String toString()
    {
        return "VO{" +
                "userList=" + userList +
                '}';
    }
}
