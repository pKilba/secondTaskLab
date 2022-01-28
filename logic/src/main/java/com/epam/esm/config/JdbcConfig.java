package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig implements DatasourceConfig {

    DatasourceConfig datasourceConfig;


    @Autowired
    public JdbcConfig(DatasourceConfig datasourceConfig) {
        this.datasourceConfig = datasourceConfig;
    }

    @Bean
    public HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(datasourceConfig.getJdbcUrl());
        config.setUsername(datasourceConfig.getUsername());
        config.setDriverClassName(datasourceConfig.getDriverClassName());
        config.setPassword(datasourceConfig.getPassword());
        config.setMaximumPoolSize(datasourceConfig.getMaxPoolSize());

        return config;
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }















    //todo исправить!!
    @Override
    public String getDriverClassName() {
        return datasourceConfig.getDriverClassName();
    }

    @Override
    public String getJdbcUrl() {
        return datasourceConfig.getJdbcUrl();
    }

    @Override
    public String getUsername() {
        return datasourceConfig.getUsername();
    }

    @Override
    public String getPassword() {
        return datasourceConfig.getPassword();
    }

    @Override
    public int getMaxPoolSize() {
        return datasourceConfig.getMaxPoolSize();
    }
}