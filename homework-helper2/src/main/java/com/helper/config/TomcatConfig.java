package com.helper.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//也可以通过实现WebServerFactoryCustomizer
@Configuration
public class TomcatConfig {
    @Bean
    public ConfigurableServletWebServerFactory factory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8080);
        factory.setContextPath("/api");
        return factory;
    }


}
