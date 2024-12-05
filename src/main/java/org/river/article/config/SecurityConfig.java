package org.river.article.config;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.river.article.filter.JwtAuthenticationFilter;
import org.river.article.service.impl.UserLoginDetailsServiceImpl;
import org.river.article.utils.springContext.SpringContextUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Resource(type = UserLoginDetailsServiceImpl.class)
    private final UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> {
//            authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/hello").permitAll();
            authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/login").permitAll();
            authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/hello").hasAuthority("getUserInfo");
        });

        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        // 禁用登出页面
        httpSecurity.logout(AbstractHttpConfigurer::disable);
        // 禁用session
        httpSecurity.sessionManagement(AbstractHttpConfigurer::disable);
        // 禁用httpBasic
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        // 禁用csrf保护
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // 通过上下文获取AuthenticationManager
        AuthenticationManager authenticationManager = SpringContextUtils.getBean("authenticationManager");
        // 添加自定义token验证过滤器
        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }
}
