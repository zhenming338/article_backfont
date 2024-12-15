package org.river.article.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleCardDto {
    String title;
    String context;
    String authorName;
}
