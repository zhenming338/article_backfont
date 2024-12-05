package org.river.article.controller;

import jakarta.annotation.Resource;
import org.river.article.common.Result;
import org.river.article.pojo.vo.UserVo;
import org.river.article.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/getUserInfo")
    public Result<UserVo>  getUserInfo(){
        UserVo userVo = userService.getUserInfo();
        return Result.success(userVo);
    }
}
