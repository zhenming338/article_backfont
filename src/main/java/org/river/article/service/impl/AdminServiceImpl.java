package org.river.article.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.river.article.mapper.AuthorityMapper;
import org.river.article.mapper.RoleMapper;
import org.river.article.pojo.entity.Authority;
import org.river.article.pojo.entity.Role;
import org.river.article.pojo.vo.AuthoritiesFixVo;
import org.river.article.pojo.vo.AuthoritiesVo;
import org.river.article.service.AdminService;
import org.river.article.utils.springContext.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public AuthoritiesVo getAuthorityList() {
        ArrayList<AuthoritiesFixVo> authoritiesFixVos = new ArrayList<>();
        List<Role> roleList = roleMapper.getRoleListByUsername(BaseContext.getContext());

        for (Role role : roleList) {
            if (!role.getName().equals("user")) {

                AuthoritiesFixVo authoritiesFixVo = new AuthoritiesFixVo();
                authoritiesFixVo.setName(role.getLabel());

                ArrayList<Authority> authorities = authorityMapper.getAuthorityListByRoleId(role.getId());

                authoritiesFixVo.setAuthorities(authorities);
            }
        }
        AuthoritiesVo authoritiesVo = new AuthoritiesVo();
        authoritiesVo.setAuthoritiesFixVos(authoritiesFixVos);
        return authoritiesVo;
    }

}
