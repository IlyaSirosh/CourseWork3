package com.sirosh.app.config;

import com.sirosh.app.aspect.StatsAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

/**
 * Created by Illya on 5/25/17.
 */

@Configuration
@ComponentScan(basePackages = "com.sirosh.app")
@PropertySource("classpath:db.properties")
@EnableWebMvc
@EnableAspectJAutoProxy
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Environment env;

    @Bean
    public StatsAspect statsAspect(){
        return new StatsAspect();
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
//        dataSource.setSchema(env.getProperty("db.schema"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }



    @Bean
    public ViewResolver jspViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("views/");
        resolver.setSuffix(".jsp");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }
}
