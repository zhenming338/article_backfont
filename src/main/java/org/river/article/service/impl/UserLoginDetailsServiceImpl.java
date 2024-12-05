package org.river.article.service.impl;

import lombok.RequiredArgsConstructor;
import org.river.article.mapper.AuthorityMapper;
import org.river.article.mapper.RoleMapper;
import org.river.article.mapper.UserMapper;
import org.river.article.pojo.entity.Authority;
import org.river.article.pojo.entity.Role;
import org.river.article.pojo.entity.User;
import org.river.article.utils.springContext.BaseContext;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class UserLoginDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final AuthorityMapper authorityMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        String role = BaseContext.getContext();
        List<Role> roles = roleMapper.getRoleListByUsername(username);
        boolean hasRole = false;
        for (Role roleItem : roles) {
            if (roleItem.getName().equals(role)) {
                hasRole = true;
                break;
            }
        }
        if (!hasRole) {
            throw new RuntimeException("user" + username + "has no role" + role);
        }

        List<Authority> authorityList = authorityMapper.getAuthorityListByUsername(username);
        StringJoiner stringJoiner = new StringJoiner(",", "", "");
        authorityList.forEach(authority -> stringJoiner.add(authority.getName()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(stringJoiner.toString()));
    }
}
