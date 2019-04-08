package com.jd.framework.config;



import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jd.project.utils.AppFilterUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppFilterConfig {

    @Bean
    public FilterRegistrationBean appTimeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        AppFilterUtils appFilter = new AppFilterUtils();
        filterRegistrationBean.setFilter(appFilter);        //设置自己配置的过滤器
        List<String> urls = new ArrayList<>();
        urls.add("/app/*");
        filterRegistrationBean.setUrlPatterns(urls);        //配置过滤规则
        filterRegistrationBean.setName("AppFilterUtils");   //过滤器类名
        //filterRegistrationBean.setOrder(1);               //如果有多个过滤器，设置优先顺序
        return filterRegistrationBean;
    }

}
