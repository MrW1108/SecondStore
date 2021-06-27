package com.example.SecondStore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {
    private String UPLOAD_FOLDER = "C:\\Users\\Administrator\\Desktop\\软件开发实践\\img";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + UPLOAD_FOLDER + "/");
    }
}
