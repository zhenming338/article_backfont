package org.river.article.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.RuntimeErrorException;

import org.river.article.common.Result;
import org.river.article.pojo.dto.AddUserDto;
import org.river.article.pojo.entity.Role;
import org.river.article.pojo.vo.UserVo;
import org.river.article.service.UserService;
import org.river.article.utils.email.EmailUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * @param addUserDto
     * @return
     */
    @PostMapping("/register")
    public Result<Object> register(@RequestBody AddUserDto addUserDto) {
        System.out.println(emailCodeMap);
        System.out.println(addUserDto);
        String email = addUserDto.getEmail();
        String code = addUserDto.getCode();
        if(emailCodeMap!=null){
            System.out.println(emailCodeMap.get(email));
            System.out.println(code);
            if(!emailCodeMap.get(email).equals(code)){
                throw new RuntimeErrorException(null, "验证码错误");
            }
        }else{
            throw new RuntimeErrorException(null,"邮箱类初始化失败");
        }

        userService.addUserByAddUserDto(addUserDto);

        return Result.success();
    }

    @GetMapping("/getRoleList")
    public Result<List<Role>> getRoleList() {
        List<Role> roleList=userService.getRoleList();
        return Result.success(roleList);
    }
    
}
