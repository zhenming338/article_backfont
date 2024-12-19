package org.river.article.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.catalina.mbeans.UserMBean;
import org.river.article.common.Page;
import org.river.article.mapper.ArticleMapper;
import org.river.article.mapper.AuthorityMapper;
import org.river.article.mapper.ChannelMapper;
import org.river.article.mapper.RoleMapper;
import org.river.article.mapper.UserMapper;
import org.river.article.pojo.dto.ChangeState;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Article;
import org.river.article.pojo.entity.Authority;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.entity.Role;
import org.river.article.pojo.entity.User;
import org.river.article.pojo.vo.ArticlePageVO;
import org.river.article.pojo.vo.AuthoritiesFixVo;
import org.river.article.pojo.vo.AuthoritiesVo;
import org.river.article.pojo.vo.UserVo;
import org.river.article.service.AdminService;
import org.river.article.utils.springContext.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    ChannelMapper channelMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public AuthoritiesVo getAuthorityList() {
        ArrayList<AuthoritiesFixVo> authoritiesFixVos = new ArrayList<>();
        List<Role> roleList = roleMapper.getRoleListByUsername(BaseContext.getContext());

        for (Role role : roleList) {
            if (!role.getName().equals("user")) {

                AuthoritiesFixVo authoritiesFixVo = new AuthoritiesFixVo();
                authoritiesFixVo.setName(role.getLabel());

                ArrayList<Authority> authorities = authorityMapper.getAuthorityListByRoleId(role.getId());

                authoritiesFixVo.setAuthorities(authorities);
            }
        }
        AuthoritiesVo authoritiesVo = new AuthoritiesVo();
        authoritiesVo.setAuthoritiesFixVos(authoritiesFixVos);
        return authoritiesVo;
    }

    @Override
    public Page<ArticlePageVO> getArticlePage(GetArticleListPageDto getArticleListPageDto) {
        if (getArticleListPageDto.getPageIndex() == null || getArticleListPageDto.getPageIndex() <= 0) {
            getArticleListPageDto.setPageIndex(1);
        }
        if (getArticleListPageDto.getPageSize() == null || getArticleListPageDto.getPageSize() <= 0) {
            getArticleListPageDto.setPageSize(10);
        }
        getArticleListPageDto
                .setStartPageIndex((getArticleListPageDto.getPageIndex() - 1) * getArticleListPageDto.getPageSize());

        List<Article> articleList = articleMapper.getArticlePage(getArticleListPageDto);
        int articleCount = articleMapper.getArticleCountByPageDto(getArticleListPageDto);

        List<ArticlePageVO> articlePageVOList = new ArrayList<ArticlePageVO>();
        for (Article article : articleList) {
            ArticlePageVO articlePageVO = new ArticlePageVO();
            User user = userMapper.getUserById(article.getAuthorId());
            Channel channel = channelMapper.getChannelById(article.getChannelId());
            BeanUtils.copyProperties(article, articlePageVO);
            articlePageVO.setAuthorName(user.getUsername());
            articlePageVO.setChannelName(channel.getName());
            articlePageVOList.add(articlePageVO);
        }
        return new Page<>(articleCount, articlePageVOList);
    }

    @Override
    public ArrayList<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public void changeChannelName(Channel channel) {
        channelMapper.modifyChannel(channel);
    }

    @Override
    @Transactional()
    public void deleteChannelByChannelId(Integer channelId) {
        articleMapper.deleteArticlesByChannelId(channelId);
        channelMapper.deleteById(channelId);
    }

    @Override
    public void changeArticleState(ChangeState changeState) {
        articleMapper.changeState(changeState);
    }

    @Override
    public ArrayList<UserVo> getAllUserVo() {
        ArrayList<UserVo> userVoList = new ArrayList<>();
        ArrayList<User> allUser = userMapper.getAllUser();
        for (User user : allUser) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            List<Role> roleList = roleMapper.getRoleListByUserId(user.getId());
            userVo.setRoleList(roleList);
            userVoList.add(userVo);
        }
        return userVoList;
    }

    @Override
    public void deleteUserByUserId(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void changeUserState(User user) {
        userMapper.changeUserState(user);
    }

}
