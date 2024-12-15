package org.river.article.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.river.article.common.Result;
import org.river.article.pojo.vo.UserVo;
import org.river.article.service.UserService;
import org.river.article.utils.email.EmailUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/getUserInfo")
    public Result<UserVo> getUserInfo() {
        UserVo userVo = userService.getUserInfo();
        return Result.success(userVo);
    }

    private static final EmailUtil emailUtil = new EmailUtil();
    private static final Map<String, String> emailCodeMap = new ConcurrentHashMap<>();

    @GetMapping("/sendCode")
    public Result<String> sendVerificationCode(@RequestParam String email) {
        try {
            System.out.println(email);
            String code = String.valueOf((int) (Math.random() * 9000) + 1000);
            emailUtil.sendVerificationCode(email, code);
            emailCodeMap.put(email, code);
            Result<String> result = new Result<>();
            result.setMessage("验证码已发至邮箱");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("验证码发送失败,请检查邮箱地址");
        }
    }


}
