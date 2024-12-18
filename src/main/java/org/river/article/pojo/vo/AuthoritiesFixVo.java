package org.river.article.pojo.vo;

import java.util.ArrayList;

import org.river.article.pojo.entity.Authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthoritiesFixVo {
    String name;
    ArrayList<Authority> authorities;
}
