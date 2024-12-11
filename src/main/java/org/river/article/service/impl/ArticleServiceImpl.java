package org.river.article.service.impl;

import lombok.RequiredArgsConstructor;
import org.river.article.common.Page;
import org.river.article.constant.ArticleConstant;
import org.river.article.mapper.ArticleMapper;
import org.river.article.mapper.ChannelMapper;
import org.river.article.mapper.UserMapper;
import org.river.article.pojo.dto.AddArticleDto;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Article;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.entity.User;
import org.river.article.pojo.vo.ArticlePageVO;
import org.river.article.service.ArticleService;
import org.river.article.utils.springContext.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ChannelMapper channelMapper;

    private final ArticleMapper articleMapper;

    private final UserMapper userMapper;

    @Override
    public List<Channel> getChannelList() {
        return channelMapper.getChannelList();
    }

    @Override
    public void addArticle(AddArticleDto addArticleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(addArticleDto, article);
        String username = BaseContext.getContext();
        User user = userMapper.getUserByUsername(username);
        article.setAuthorId(user.getId());
        article.setStatus(ArticleConstant.ARTICLE_STATE_WAIT);
        articleMapper.addArticle(article);
    }

    @Override
    public Page<ArticlePageVO> getArticlePage(GetArticleListPageDto getArticleListPageDto) {
        if (getArticleListPageDto.getPageIndex() == null || getArticleListPageDto.getPageIndex() <= 0) {
            getArticleListPageDto.setPageIndex(1);
        }
        if (getArticleListPageDto.getPageSize() == null || getArticleListPageDto.getPageSize() <= 0) {
            getArticleListPageDto.setPageSize(10);
        }
        getArticleListPageDto.setStartPageIndex((getArticleListPageDto.getPageIndex() - 1) * getArticleListPageDto.getPageSize());

        List<Article> articleList = articleMapper.getArticlePage(getArticleListPageDto);
        int articleCount = articleMapper.getArticleCountByPageDto(getArticleListPageDto);

        List<ArticlePageVO> articlePageVOList = new ArrayList<ArticlePageVO>();
        for (Article article : articleList) {
            ArticlePageVO articlePageVO = new ArticlePageVO();
            User user=userMapper.getUserById(article.getAuthorId());
            Channel channel = channelMapper.getChannelById(article.getChannelId());
            BeanUtils.copyProperties(article, articlePageVO);
            articlePageVO.setAuthorName(user.getUsername());
            articlePageVO.setChannelName(channel.getName());
            articlePageVOList.add(articlePageVO);
        }
        return new Page<>(articleCount, articlePageVOList);
    }
}