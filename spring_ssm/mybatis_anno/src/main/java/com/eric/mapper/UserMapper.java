package com.eric.mapper;

import com.eric.domain.User;
import com.eric.handler.DateTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-13 09:33
 */
public interface UserMapper {

    /**
     * insert user
     *
     * @param user user
     */
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday", typeHandler = DateTypeHandler.class)
    })
    @Insert("insert into user values(#{id},#{username},#{password},#{birthday})")
    void save(User user);

    /**
     * update user
     *
     * @param user user
     */
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    void update(User user);

    /**
     * delete user
     *
     * @param id user id
     */

    @Delete("delete from user where id=#{id}")
    void delete(int id);

    /**
     * find user by id
     *
     * @param id user id
     * @return user list
     */
    @Select("select * from user where id=#{id}")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            // @Result(column = "username", property = "username"),
            // @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday", typeHandler = DateTypeHandler.class)
    })
    User findById(int id);

    /**
     * find all user
     *
     * @return user list
     */
    @Select("select * from user")
    @Results(value = {
            @Result(column = "birthday", property = "birthday", typeHandler = DateTypeHandler.class)
    })
    List<User> findAll();

    /**
     * find all users with order list
     *
     * @return users with order list
     */
    @Select("select * from user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday", typeHandler = DateTypeHandler.class),
            @Result(
                    column = "id",
                    property = "orderList",
                    many = @Many(select = "com.eric.mapper.OrderMapper.findOrdersByUserId")
            )
    })
    List<User> findUserAndOrders();

    /**
     * find all users with order and role list
     *
     * @return users with order and role list
     */
    @Select("select * from user")
    @Results({

            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday", typeHandler = DateTypeHandler.class),
            @Result(
                    column = "id",
                    property = "orderList",
                    many = @Many(select = "com.eric.mapper.OrderMapper.findOrdersByUserId")
            ),
            @Result(
                    column = "id",
                    property = "roleList",
                    many = @Many(select = "com.eric.mapper.RoleMapper.findRolesByUserId")
            )
    })
    List<User> findAllUserWithRolesAndOrders();
}
