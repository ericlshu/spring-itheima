package com.eric.domain;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-14 13:35
 */
public class Account {
    private Integer id;
    private String name;
    private Double money;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getMoney()
    {
        return money;
    }

    public void setMoney(Double money)
    {
        this.money = money;
    }

    @Override
    public String toString()
    {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
