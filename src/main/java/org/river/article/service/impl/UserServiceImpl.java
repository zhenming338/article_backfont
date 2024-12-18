package org.river.article.service.impl;

import jakarta.annotation.Resource;

import java.util.List;

import org.river.article.mapper.RoleMapper;
import org.river.article.mapper.UserMapper;
import org.river.article.pojo.dto.AddUserDto;
import org.river.article.pojo.entity.Role;
import org.river.article.pojo.entity.User;
import org.river.article.pojo.vo.UserVo;
import org.river.article.service.UserService;
import org.river.article.utils.springContext.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserVo getUserInfo() {
        String username = BaseContext.getContext();
        User user = userMapper.getUserByUsername(username);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        // userVo.setRoleName(roleMapper.getNameByUserId(user.getId()));
        userVo.setRoleList(roleMapper.getRoleListByUsername(user.getUsername()));
        return userVo;
    }

    @Override
    public void addUserByAddUserDto(AddUserDto addUserDto) {
        User user = new User();
        addUserDto.setPassword(bCryptPasswordEncoder.encode(addUserDto.getPassword()));
        BeanUtils.copyProperties(addUserDto, user);
        user.setEnabled(true);
        System.out.println(user);
        userMapper.addUser(user);
        Integer roleId = roleMapper.getIdByName("user");
        roleMapper.addUserByUserIdAndRoleId(user.getId(), roleId);

    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = roleMapper.getRoleList();
        return roleList;
    }
}
