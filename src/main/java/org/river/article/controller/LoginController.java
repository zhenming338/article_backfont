package org.river.article.controller;

import lombok.RequiredArgsConstructor;
import org.river.article.common.Result;
import org.river.article.mapper.UserMapper;
import org.river.article.pojo.dto.UserLoginDto;
import org.river.article.pojo.entity.User;
import org.river.article.service.impl.UserLoginDetailsServiceImpl;
import org.river.article.utils.jwt.JwtTokenUtils;
import org.river.article.utils.springContext.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {


    private final JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserLoginDetailsServiceImpl userLoginDetailsServiceImpl;

    @Autowired 
    UserMapper userMapper;

    @Autowired
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("hello river");
    }


    @PostMapping("/user/login")
    public Result<Object> login(@RequestBody UserLoginDto userLoginDto) {
        System.out.println(userLoginDto);
        try {
            BaseContext.setContext(userLoginDto.getRole());
            UserDetails userDetails = userLoginDetailsServiceImpl.loadUserByUsername(userLoginDto.getUsername());

            String passwordString = bCryptPasswordEncoder.encode(userLoginDto.getPassword());
            System.out.println(passwordString);
            User user = userMapper.getUserByUsername(userLoginDto.getUsername());
            if (!bCryptPasswordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
                throw new RuntimeException("密码错误");
            }
            StringJoiner authorityString = new StringJoiner(",", "", "");
            userDetails.getAuthorities().forEach(authority
                    -> authorityString.add(authority.getAuthority()));
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", userLoginDto.getUsername());
            claims.put("role", userLoginDto.getRole());
            claims.put("authorities", authorityString.toString());
            String jwtToken = jwtTokenUtils.getJwtToken(claims);
            return Result.success(jwtToken);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
