package org.river.article.config.security;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Resource(type = UserLoginDetailsServiceImpl.class)
    private final UserDetailsService userDetailsService;

    private final AuthAccessDeniedHandler authAccessDeniedHandler;

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

    CorsConfigurationSource corsConfiguration() {
        // Cors配置类
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false); // 是否返回时生成凭证
        corsConfiguration.setAllowedHeaders(List.of("*")); // 允许请求携带哪些请求头信息
        corsConfiguration.setAllowedMethods(List.of("*")); // 允许哪些类型的请求方法
        corsConfiguration.setAllowedOrigins(List.of("*")); // 允许哪些域可以进行方法
        corsConfiguration.setMaxAge(3600L); // 设置预检的最大的时长
        corsConfiguration.setExposedHeaders(Collections.emptyList()); // 设置返回暴露的响应头信息

        // 设置注册URL 配置类
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity,
                                                          AuthEntryPointHandler authEntryPointHandler) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> {
            authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/api/user/login").permitAll();
            authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/api/user/register").permitAll();
            authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/api/user/sendCode").permitAll();
            authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/api/user/getRoleList").permitAll();
            authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/**").hasAuthority("allAuthority");
            authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/**").hasAuthority("allAuthority");
            authorizeHttpRequests.requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("allAuthority");
            authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/api/user/**").hasAuthority("getUserInfo");
            authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/api/user/**").hasAuthority("getUserInfo");
            authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/api/article/**").hasAuthority("getUserInfo");
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

        httpSecurity.cors(cors -> {
            cors.configurationSource(corsConfiguration());
        });
        httpSecurity.exceptionHandling(exceptionHandling -> {
            exceptionHandling.accessDeniedHandler(authAccessDeniedHandler);
            exceptionHandling.authenticationEntryPoint(authEntryPointHandler);
        });
        return httpSecurity.build();

    }
}
