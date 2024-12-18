package org.river.article.pojo.vo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.management.relation.Role;

import org.river.article.pojo.entity.Authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthoritiesVo {
    // ArrayList<Role> roleList;
    // ArrayList<Authority> authorityList;

    // HashMap<String, ArrayList<Authority>> autohrityList;

    ArrayList<AuthoritiesFixVo> authoritiesFixVos;
}
