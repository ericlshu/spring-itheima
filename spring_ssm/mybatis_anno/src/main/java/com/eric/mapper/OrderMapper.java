package com.eric.mapper;

import com.eric.domain.Order;
import com.eric.domain.User;
import com.eric.handler.DateTypeHandler;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-13 15:33
 */
public interface OrderMapper {
    /**
     * find all orders with user
     * by single query scripts
     *
     * @return order list
     */
    @Select("select *, o.id as oid\n" +
            "from `order` o,\n" +
            "     user u\n" +
            "where o.uid = u.id")
    @Results({
            @Result(id = true, column = "oid", property = "id"),
            @Result(column = "total", property = "total"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(column = "uid", property = "user.id"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "password", property = "user.password"),
            @Result(column = "birthday", property = "user.birthday", typeHandler = DateTypeHandler.class)
    })
    List<Order> findAllOrderWithUser1();


    /**
     * find all orders with user
     * by one to one mapping
     *
     * @return order list
     */
    @Select("select * from `order`")
    @Results({
            @Result(id = true, column = "oid", property = "id"),
            @Result(column = "total", property = "total"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(
                    property = "user",     //要封装的属性名称
                    column = "uid",        //根据哪个字段去查询user表的数据
                    javaType = User.class, //要封装的实体类型
                    // select属性 代表查询哪个接口的方法获得数据
                    one = @One(select = "com.eric.mapper.UserMapper.findById")
            )
    })
    List<Order> findAllOrderWithUser2();
}
