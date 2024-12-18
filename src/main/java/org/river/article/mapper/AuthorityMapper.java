package org.river.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.river.article.pojo.entity.Authority;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface AuthorityMapper {
    List<Authority> getAuthorityListByUsername(String username);

    ArrayList<Authority> getAuthorityListByRoleId(int roleId);
}
