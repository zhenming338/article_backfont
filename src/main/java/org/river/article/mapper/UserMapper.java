package org.river.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.river.article.pojo.entity.User;

@Mapper
public interface UserMapper {
    User getUserByUsername(@Param("username") String username);
}
