package com.eric.domain;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 22:46
 */
public class User {

    private String name;
    private String addr;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
