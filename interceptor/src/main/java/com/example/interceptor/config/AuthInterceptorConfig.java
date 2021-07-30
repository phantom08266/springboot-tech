package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    /users/login, /settings 경로를 제외한 모든 요청은 로그인을 해야 이용할 수 있도록interceptor설정
 */
@RequiredArgsConstructor
@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/profiles")
                .addPathPatterns("/users")
                .excludePathPatterns("/users/login");
        registry.addInterceptor(authInterceptor).addPathPatterns("/profiles");
    }
}
