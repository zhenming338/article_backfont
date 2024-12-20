package org.river.article.pojo.vo;

import org.river.article.pojo.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageVO extends Article {
    String authorName;
    String channelName;
}
