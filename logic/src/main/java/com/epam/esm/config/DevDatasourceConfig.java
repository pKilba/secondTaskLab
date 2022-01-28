package com.epam.esm.config;

import org.springframework.stereotype.Component;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-dev.properties")
@Profile("dev")
@Component
public class DevDatasourceConfig implements DatasourceConfig {
    @Value("${dataSource.db.driverClassName}")
    String driverClassName;

    @Value("${dataSource.db.jdbcUrl}")
    String jdbcUrl;

    @Value("${dataSource.db.user}")
    String username;

    @Value("${dataSource.db.password}")
    String password;
    @Value("${dataSource.db.maxPoolSize}")
    int maxPoolSize;

    public String getDriverClassName() {
        return driverClassName;
    }


    public String getJdbcUrl() {
        return jdbcUrl;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
