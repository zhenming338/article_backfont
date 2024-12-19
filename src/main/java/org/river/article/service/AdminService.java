package org.river.article.service;

import java.util.ArrayList;

import org.river.article.common.Page;
import org.river.article.pojo.dto.ChangeState;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.entity.User;
import org.river.article.pojo.vo.ArticlePageVO;
import org.river.article.pojo.vo.AuthoritiesVo;
import org.river.article.pojo.vo.UserVo;

public interface AdminService {

    AuthoritiesVo getAuthorityList();

    Page<ArticlePageVO> getArticlePage(GetArticleListPageDto getArticleListPageDto);

    ArrayList<User> getAllUser();

    void changeChannelName(Channel channel);

    void deleteChannelByChannelId(Integer channelId);

    void changeArticleState(ChangeState changeState);

    ArrayList<UserVo> getAllUserVo();

    void deleteUserByUserId(Integer id);

    void changeUserState(User user);

}
