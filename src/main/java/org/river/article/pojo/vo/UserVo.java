package org.river.article.pojo.vo;

import java.time.LocalDateTime;
import java.util.List;

import org.river.article.pojo.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    String username;
    int id;
    String phone;
    String email;
    LocalDateTime createTime;
    List<Role> roleList;
}
