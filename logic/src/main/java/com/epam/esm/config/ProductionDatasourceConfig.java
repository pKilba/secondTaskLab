package com.epam.esm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@Profile("prod")
@PropertySource("classpath:application-production.properties")
public class ProductionDatasourceConfig implements DatasourceConfig {
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

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
}
