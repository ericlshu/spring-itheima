package com.eric.domain;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 20:11
 */
public class Account {
    private String name;
    private double money;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getMoney()
    {
        return money;
    }

    public void setMoney(double money)
    {
        this.money = money;
    }

    @Override
    public String toString()
    {
        return "Account{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}

