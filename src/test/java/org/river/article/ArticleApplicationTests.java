package org.river.article;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.river.article.mapper.AuthorityMapper;
import org.river.article.mapper.RoleMapper;
import org.river.article.mapper.UserMapper;
import org.river.article.pojo.entity.Authority;
import org.river.article.pojo.entity.Role;
import org.river.article.pojo.entity.User;
import org.river.article.utils.jwt.JwtTokenProperties;
import org.river.article.utils.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ArticleApplicationTests {


    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Autowired
    RoleMapper roleMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void testGetUserByUsername() {
        User userByUsername = userMapper.getUserByUsername("river");
        System.out.println(userByUsername);
    }

    @Test
    void testGetAuthorityListByUsername() {
        List<Authority> river = authorityMapper.getAuthorityListByUsername("river");
        System.out.println(river);
    }

    @Test
    void getEncodePassword() {
        String password = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        System.out.println(encode);
    }

    @Test
    void testJwtToken() {

        JwtTokenProperties jwtTokenProperties = new JwtTokenProperties();
        jwtTokenProperties.setExpire(1000000000l);
        jwtTokenProperties.setSigningKey("elfkajefoiajflaksejfla");
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils(jwtTokenProperties);
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("username", "river");
        stringObjectMap.put("password", "123456");
        String jwtToken = jwtTokenUtils.getJwtToken(stringObjectMap);
        System.out.println(jwtToken);

        Claims claims = jwtTokenUtils.parseJwtToken(jwtToken);
        System.out.println(claims);
    }

    @Test
    void testRoleMapper(){
        List<Role> river = roleMapper.getRoleListByUsername("river");
        System.out.println(river);
    }
}
