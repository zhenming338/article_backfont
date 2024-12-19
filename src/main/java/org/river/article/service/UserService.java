package org.river.article.service;

import java.util.List;

import org.river.article.pojo.dto.AddUserDto;
import org.river.article.pojo.entity.Role;
import org.river.article.pojo.entity.User;
import org.river.article.pojo.vo.UserVo;

public interface UserService {

    UserVo getUserInfo();

    void addUserByAddUserDto(AddUserDto addUserDto);

    List<Role> getRoleList();

    void editUserInfo(User user);

}
