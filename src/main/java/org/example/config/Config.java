package org.example.config;

import org.example.controller.BookController;
import org.example.dao.BookDaoImpl;
import org.example.dao.CustomerDaoImpl;
import org.example.dao.GenreDaoImpl;
import org.example.repository.BookDao;
import org.example.repository.CustomerDao;
import org.example.repository.GenreDao;
import org.example.services.BookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Objects;
import java.util.Properties;

@Configuration
public class Config {
    private Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setPackagesToScan("org.example.entities");

        entityManagerFactory.setJpaProperties(hibernateJpaProperties());
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    private Properties hibernateJpaProperties() {
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.DRIVER, environment.getProperty("spring.datasource.driverClassName"));
        properties.put(org.hibernate.cfg.Environment.URL, environment.getProperty("spring.datasource.url"));
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
        properties.put(org.hibernate.cfg.Environment.USER, environment.getProperty("spring.datasource.username"));
        properties.put(org.hibernate.cfg.Environment.PASS, environment.getProperty("spring.datasource.password"));
        return properties;
    }
}
