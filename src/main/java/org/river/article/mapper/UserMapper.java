package org.river.article.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.river.article.pojo.entity.User;

@Mapper
public interface UserMapper {
    User getUserByUsername(@Param("username") String username);

    User getUserById(Integer id);

    void addUser(User user);

    ArrayList<User> getAllUser();

    void deleteUserById(Integer id);

    void changeUserState(User user);
}
