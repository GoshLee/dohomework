package com.helper.config;

import com.helper.ConstantBean;
import com.helper.interceptor.HomeworkInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * SpringMVC  配置类
 * @author SouthLight
 */
@Configuration
@EnableWebMvc
@ConditionalOnBean(ConstantBean.class)
public class MVCApplicationConfig  implements WebMvcConfigurer {




    @Autowired
    private ConstantBean constantConfig;







    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HomeworkInterceptor()).addPathPatterns("/index",
                "/", "/admin/**", "/teacher/**", "/stu/**")
        .excludePathPatterns("/teacher/login")
        .excludePathPatterns("/stu/login")
        .excludePathPatterns("/admin/login");
    }





}
