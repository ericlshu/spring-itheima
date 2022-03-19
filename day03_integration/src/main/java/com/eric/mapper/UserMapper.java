package com.eric.mapper;

import com.eric.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-19 20:30
 */
@Mapper
public interface UserMapper {
    /**
     * get user by id
     *
     * @param id user id
     * @return user
     */
    @Select("select * from tb_user where id = #{id}")
    User getById(Integer id);


    /**
     * find all user
     *
     * @return user list
     */
    @Select("select * from tb_user")
    List<User> findAll();
}
