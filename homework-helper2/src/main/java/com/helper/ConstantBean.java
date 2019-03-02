package com.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 这是一个存放变量的Bean
 * 变量的值直接跟application.properties里面的值映射
 * 所以可以直接在application.properties里面设置值
 * 这样就可以映射到这个Bean中，供其他组件使用
 * @author SouthLight
 */
@Component
public class ConstantBean {
    @Value("${static_file_path}")
    public String static_file_path;

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
}
