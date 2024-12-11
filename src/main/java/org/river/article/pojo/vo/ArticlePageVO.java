package org.river.article.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.river.article.pojo.entity.Article;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageVO extends Article {
    String authorName;
    String channelName;
}
