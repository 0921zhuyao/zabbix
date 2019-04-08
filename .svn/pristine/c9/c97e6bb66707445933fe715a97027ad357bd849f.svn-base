package com.jd.framework.config;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultipartConfig {

    /**
     * 文件上传临时路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String path=System.getProperty("user.dir")+File.separator+"fileTemp"+File.separator;
        File file=new File(path);
        if(!file.exists()) {
        	file.mkdirs();
        }
        factory.setLocation(path);
        return factory.createMultipartConfig();
    }
}
