package org.river.article.service;

import org.river.article.common.Page;
import org.river.article.pojo.dto.AddArticleDto;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Article;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.vo.ArticlePageVO;

import java.util.List;

public interface ArticleService {
      List<Channel> getChannelList() ;

    void addArticle(AddArticleDto addArticleDto);

    Page<ArticlePageVO> getArticlePage(GetArticleListPageDto getArticleListPageDto);
}
