package org.river.article.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.river.article.pojo.entity.Article;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleListPageDto {
    String title;
    int authorId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    int channelId;
    int status;
    Integer pageIndex;
    Integer startPageIndex;
    Integer pageSize;
}
