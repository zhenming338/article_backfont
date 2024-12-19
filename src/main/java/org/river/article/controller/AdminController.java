package org.river.article.controller;

import java.util.ArrayList;

import org.apache.ibatis.executor.ResultExtractor;
import org.river.article.common.Page;
import org.river.article.common.Result;
import org.river.article.pojo.dto.ChangeState;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.entity.User;
import org.river.article.pojo.vo.ArticlePageVO;
import org.river.article.pojo.vo.AuthoritiesVo;
import org.river.article.pojo.vo.UserVo;
import org.river.article.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @GetMapping("/getAuthorityList")
    public Result<AuthoritiesVo> getAuthorityList() {
        AuthoritiesVo authoritiesVo = adminService.getAuthorityList();
        System.out.println(authoritiesVo);
        return Result.success(authoritiesVo);
    }

    @PostMapping("/getArticlePage")
    public Result<Page<ArticlePageVO>> getAllArticleByArticlePageDto(
            @RequestBody GetArticleListPageDto getArticleListPageDto) {
        System.out.println(getArticleListPageDto);

        Page<ArticlePageVO> articlePage = adminService.getArticlePage(getArticleListPageDto);
        return Result.success(articlePage);
    }

    @GetMapping("/getAllUser")
    public Result<ArrayList<User>> getAllUser() {

        return Result.success(adminService.getAllUser());
    }

    @GetMapping("/getAllUserVo")
    public Result<ArrayList<UserVo>> getAllUserVo() {

        return Result.success(adminService.getAllUserVo());
    }

    @PostMapping("/modifyChannel")
    public Result<Object> changeChannelName(@RequestBody Channel channel) {
        adminService.changeChannelName(channel);
        return Result.success();
    }

    @PostMapping("/deleteChannel")
    public Result<Object> deleteChannel(@RequestBody Channel channel) {
        adminService.deleteChannelByChannelId(channel.getId());
        return Result.success();
    }

    @PostMapping("/changeArticleState")
    public Result<Object> changeArticleState(@RequestBody ChangeState changeState) {
        System.out.println(changeState);
        adminService.changeArticleState(changeState);
        return Result.success();
    }

    // @PostMapping("/getAllUsere")
    // public Result<User> getAllUserPage(@RequestBody GetArticleListPageDto
    // getArticleListPageDto) {
    // adminService.getAllUser();
    // return Result.success();
    // }

    @GetMapping("/deleteUser")
    public Result<Object> deleteUser(@RequestParam Integer id) {
        adminService.deleteUserByUserId(id);
        return Result.success();
    }

    @PostMapping("/changeUserState")
    public Result<Object> changeUserState(@RequestBody User user) {
        adminService.changeUserState(user);
        return Result.success();
    }

}
