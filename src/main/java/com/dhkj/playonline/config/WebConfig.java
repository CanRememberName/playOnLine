package com.dhkj.playonline.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:D:/res/pic/").addResourceLocations("file:D:/res/v6speedDownload/qiangujuecheng/")
                .addResourceLocations("file:D:/res/v6speedDownload/tianjimiyu/")
                .addResourceLocations("file:D:/res/v6speedDownload/liangyi/");
    }
}
