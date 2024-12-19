package org.river.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.river.article.pojo.dto.ChangeState;
import org.river.article.pojo.dto.GetArticleCardDto;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Article;
import org.river.article.pojo.vo.ArticleContentVo;

import java.util.List;

@Mapper
public interface ArticleMapper {
    void addArticle(Article article);

    List<Article> getArticlePage(GetArticleListPageDto getArticleListPageDto);

    int getArticleCountByPageDto(GetArticleListPageDto getArticleListPageDto   );

    void deleteArticleByArticleIdAndUserId(Integer authorId, int articleId);

    List<Article> getArticleByGetArticleCardDto(GetArticleCardDto getArticleCardDto);

    ArticleContentVo getArticleContentByArticleId(int articleId);

    List<Article> getAllArticlePage(GetArticleListPageDto getArticleListPageDto);

    void deleteArticlesByChannelId(Integer channelId);

    void changeState(ChangeState changeState);
}
