/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.config;

import java.util.Properties;
import javax.sql.DataSource;
import static org.hibernate.cfg.Environment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author PC
 */
@Configuration
@PropertySource("classpath:hibernate.properties")
public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(getProps());
        sessionFactory.setPackagesToScan("com.delivery.pojo");
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("hibernate.connection.driverClass"));
        dataSource.setUrl(env.getProperty("hibernate.connection.url"));
        dataSource.setUsername(env.getProperty("hibernate.connection.username"));
        dataSource.setPassword("hibernate.connection.password");
        return dataSource;
    }

    public Properties getProps() {
        Properties props = new Properties();
        props.setProperty(DIALECT, env.getProperty("hibernate.dialect"));
        props.setProperty(SHOW_SQL, env.getProperty("hibernate.showSql"));
        props.setProperty(ENABLE_LAZY_LOAD_NO_TRANS, "true");
        return props;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager trans = new HibernateTransactionManager();
        trans.setSessionFactory(this.getSessionFactory().getObject());
        return trans;
    }
}
