package org.river.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    private String username;
    private String password;
    private int id;
    private String email;
    private String phone;
    boolean enabled;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
