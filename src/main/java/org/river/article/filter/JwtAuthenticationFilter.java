package org.river.article.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.river.article.common.Result;
import org.river.article.utils.jwt.JwtTokenUtils;
import org.river.article.utils.springContext.BaseContext;
import org.river.article.utils.springContext.SpringContextUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {

            List<String> exclude = new ArrayList<>();
            exclude.add("login");
            exclude.add("sendCode");
            exclude.add("register");
            exclude.add("getRoleList");
            String url = request.getRequestURL().toString();

            for (String excludeWord : exclude) {
                if (url.contains(excludeWord)) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            String jwtToken = request.getHeader("token");
            String jwtTokenByFile = request.getHeader("authorization");
            System.out.println(jwtToken);
            System.out.println(jwtTokenByFile);
            if (jwtToken == null || jwtToken.isEmpty()) {
                jwtToken = jwtTokenByFile;
            }
            System.out.println(jwtToken);
            if (jwtToken.isEmpty()) {
                chain.doFilter(request, response);
                return;
            }
            JwtTokenUtils jwtTokenUtils = SpringContextUtils.getBean("jwtTokenUtils");
            if (jwtTokenUtils == null) {
                throw new ServletException("jwtTokenUtils is null");
            }
            Claims claims;

            try {
                claims = jwtTokenUtils.parseJwtToken(jwtToken);

            } catch (Exception e) {
                throw new ServletException(e);
            }
            String username = claims.get("username", String.class);
            String role = claims.get("role", String.class);
            String authorityString = claims.get("authorities", String.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    role,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(authentication.getAuthorities());
            BaseContext.setContext(username);
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            String value = new ObjectMapper().writeValueAsString(Result.error("token验证失败"));
            response.getWriter().write(value);
        }

    }
}
