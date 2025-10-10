package com.gok.food_map.config;

//import com.gok.food_map.log.interceptor.TokenInterceptor;
import com.gok.food_map.filter.StaticResourceFilter;
import com.gok.food_map.interceptor.LoginInterceptor;
import com.gok.food_map.interceptor.OperationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
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
        registry.addInterceptor(new OperationInterceptor())
                //拦截路径(拦截所有的先)
                .addPathPatterns("/**")
                // 排除静态资源路径，避免拦截器的重复
                .excludePathPatterns(
                        "/static/**",          //  /static 下的所有资源
                        "/**/*.html",          // 所有路径下的 .html 文件
                        "/**/*.css",           // 所有路径下的 .css 文件
                        "/**/*.js",            // 所有路径下的 .js 文件
                        "/**/*.png",           // 所有路径下的 .png 图片
                        "/**/*.jpg",           // 所有路径下的 .jpg 图片
                        "/**/*.jpeg"           // 所有路径下的 .jpeg 图片
                        //能想到的就这么多了
                );
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login",
                        "/error/**",
                        "/MGoods/**",
                        "/merchant/getMerchantById",
                        "/**.html",
                        "/static_resources/**",
                        "/static/img/**.png",
                        "/favicon_logosc/**");
    }
    @Resource
    private StaticResourceFilter staticResourceFilter;
    // 注册过滤器
    @Bean
    public FilterRegistrationBean<StaticResourceFilter> staticResourceFilterRegistration() {
        FilterRegistrationBean<StaticResourceFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(staticResourceFilter);
        // 过滤的路径
        registration.addUrlPatterns("/static/*", "*.html", "*.css", "*.js", "*.png", "*.jpg", "*.jpeg");
        // 过滤器的名字
        registration.setName("staticResourceFilter");
        return registration;
    }
}
