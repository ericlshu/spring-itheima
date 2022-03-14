package com.eric.mapper;

import com.eric.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-14 12:50
 */
public interface RoleMapper {

    /**
     * find roles by user id
     *
     * @param userId user id
     * @return role list
     */
    @Select("select * from user_role ur, role r where ur.roleId = r.id and ur.userId = #{userId}")
    List<Role> findRolesByUserId(int userId);
}
