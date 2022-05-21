package com.eric.dao;

import com.eric.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserDao
{
    @Insert("insert into tbl_user(name,age)values(#{name},#{age})")
    void save(User user);

    @Select("select * from tbl_user where id = #{id} ")
    User findById(Integer id);
}
