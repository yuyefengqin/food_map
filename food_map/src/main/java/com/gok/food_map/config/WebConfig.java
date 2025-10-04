package com.gok.food_map.config;

//import com.gok.food_map.log.interceptor.TokenInterceptor;
import com.gok.food_map.interceptor.AuthInterceptor;
import com.gok.food_map.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login",
                        "/error/**",
                        "/MGoods/**",
                        "/merchant/getMerchantById",
                        "/**.html",
                        "/static_resources/**",
                        "/static/img/%E5%A4%9C%E6%99%AF.png .png",
                        "/favicon_logosc/**");
    }

}
