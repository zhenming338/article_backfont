package org.river.article.service.impl;

import jakarta.annotation.Resource;
import org.river.article.mapper.UserMapper;
import org.river.article.pojo.entity.User;
import org.river.article.pojo.vo.UserVo;
import org.river.article.service.UserService;
import org.river.article.utils.springContext.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Override
    public UserVo getUserInfo() {
        String username = BaseContext.getContext();
        User user = userMapper.getUserByUsername(username);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
}
