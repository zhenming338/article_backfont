package org.river.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.river.article.pojo.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> getRoleListByUsername(String username);

    void addUserByUserIdAndRoleId(Integer userId, Integer roleId);

    Integer getIdByName(String name);

    List<Role> getRoleListByUserId(Integer userId);

    List<Role> getRoleList();

    // void addAdminByUserId(Integer userId);
}
