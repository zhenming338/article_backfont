package org.river.article.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCardVo {
    int articleId;
    int authorId;
    int likeCount;
    int collectCount;
    String authorAvatar;
    String title;
    String authorName;
    String context;
    String coverUrl;
    boolean liked;
    boolean collected;
    LocalDateTime updateTime;
    LocalDateTime createTime;
}
