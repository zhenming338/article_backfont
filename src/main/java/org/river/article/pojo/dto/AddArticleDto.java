package org.river.article.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddArticleDto {
    Integer channelId;
    String title;
    String context;
    String coverUrl;
}
