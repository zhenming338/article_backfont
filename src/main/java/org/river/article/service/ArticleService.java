package org.river.article.service;

import java.util.List;

import org.river.article.common.Page;
import org.river.article.pojo.dto.AddArticleDto;
import org.river.article.pojo.dto.GetArticleCardDto;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.vo.ArticleCardVo;
import org.river.article.pojo.vo.ArticleContentVo;
import org.river.article.pojo.vo.ArticlePageVO;

public interface ArticleService {
  List<Channel> getChannelList();

  void addArticle(AddArticleDto addArticleDto);

  Page<ArticlePageVO> getArticlePage(GetArticleListPageDto getArticleListPageDto);

  void deleteMyArticleByArticleId(int articleId);

  List<ArticleCardVo> getArticleCard(GetArticleCardDto getArticleCardDto);

  ArticleContentVo  getArticleContentByArticleId(int articleId);
}
