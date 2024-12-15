package org.river.article.pojo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleContentVo {
    String authorName;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    String context;
    String title;
    String channelName;
}
