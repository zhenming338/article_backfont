package org.river.article.pojo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
