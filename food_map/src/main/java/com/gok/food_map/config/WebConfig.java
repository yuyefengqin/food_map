package com.gok.food_map.config;

//import com.gok.food_map.log.interceptor.TokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOriginPatterns("*")
                 .allowedHeaders("*")
                 .allowedMethods("*")
                 .allowCredentials(true)
                 .maxAge(3600);
    }

}
