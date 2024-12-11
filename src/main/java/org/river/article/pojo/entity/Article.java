package org.river.article.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    Integer id;
    String title;
    Integer authorId;
    String context;
    Integer channelId;
    Integer status;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    String coverUrl;
}
