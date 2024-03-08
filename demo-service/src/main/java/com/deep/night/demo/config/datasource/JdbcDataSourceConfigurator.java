package com.deep.night.demo.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.deep.night.demo.repository",
        entityManagerFactoryRef = "jpaEntityManagerFactory",
        transactionManagerRef = "jpaEntityManager"
)
public class JdbcDataSourceConfigurator {

    @Autowired
    private Environment env;

    @Bean(name="jpaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManager() {

        LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        jpaEntityManagerFactory.setDataSource(dataSource());
        jpaEntityManagerFactory.setPackagesToScan("com.deep.night.demo");
        jpaEntityManagerFactory.setPersistenceUnitName("jpaEntityManager");

        HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        jpaEntityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("spring.jpa.hibernate.dialect",env.getProperty("spring.jpa.hibernate.dialect"));
        properties.put("spring.jpa.hibernate.ddl-auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaEntityManagerFactory.setJpaPropertyMap(properties);

        return jpaEntityManagerFactory;
    }


    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        hikariConfig.setJdbcUrl( "jdbc:log4jdbc:mariadb://"+ env.getProperty("spring.datasource.jdbcUrl"));
        hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(env.getProperty("spring.datasource.password"));

        hikariConfig.setPoolName(env.getProperty("spring.datasource.hikari.pool-name"));
        hikariConfig.setMinimumIdle(env.getProperty("spring.datasource.hikari.minimum-idle", Integer.class));
        hikariConfig.setMaximumPoolSize(env.getProperty("spring.datasource.hikari.maximum-pool-size", Integer.class));
        hikariConfig.setMaxLifetime(env.getProperty("spring.datasource.hikari.max-lifetime", Integer.class));
        hikariConfig.setConnectionTimeout(env.getProperty("spring.datasource.hikari.connection-timeout", Integer.class));
        hikariConfig.setIdleTimeout(env.getProperty("spring.datasource.hikari.idle-timeout", Integer.class));

        hikariConfig.setConnectionTestQuery(env.getProperty("spring.datasource.hikari.connection-test-query"));

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "jpaEntityManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        return transactionManager;
    }

}