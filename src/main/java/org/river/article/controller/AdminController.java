package org.river.article.controller;

import org.river.article.common.Result;
import org.river.article.pojo.vo.AuthoritiesVo;
import org.river.article.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

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
}
