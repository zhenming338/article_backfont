package org.river.article.utils.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "token.jwt")
public class JwtTokenProperties {
    private String signingKey;
    private Long expire;
}
