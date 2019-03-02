package com.helper.config;

import com.mchange.v2.c3p0.AbstractComboPooledDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
@Configuration
//@MapperScan("com.helper.mapper")
public class DataSourceConfig {
    private final static String URL = "jdbc:mysql://localhost:3306/homework2";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String DRIVERCLASS = "com.mysql.jdbc.Driver";
    private final static Log log = LogFactory.getLog(DataSourceConfig.class);
    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setJdbcUrl(URL);
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);
            dataSource.setDriverClass(DRIVERCLASS);
        } catch (PropertyVetoException e) {
            log.error(e);
        }
        return dataSource;
    }

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.helper.pojo");
        //配扫描路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapping/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error("mybatis SqlSessionFactory create error",e);
            throw new RuntimeException(e);
        }
    }
}
