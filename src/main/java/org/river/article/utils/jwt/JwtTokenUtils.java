package org.river.article.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    private final JwtTokenProperties jwtTokenProperties;

    public String getJwtToken(Map<String, Object> claims) {
        String signingKey = jwtTokenProperties.getSigningKey();
        Long expire = jwtTokenProperties.getExpire();
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    public Claims parseJwtToken(String jwtToken) {
        String signingKey = jwtTokenProperties.getSigningKey();
        return Jwts.
                parser().
                setSigningKey(signingKey)
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
