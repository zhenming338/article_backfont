package org.river.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    void addArticle(Article article);

    List<Article> getArticlePage(GetArticleListPageDto getArticleListPageDto);

    int getArticleCountByPageDto(GetArticleListPageDto getArticleListPageDto   );
}
