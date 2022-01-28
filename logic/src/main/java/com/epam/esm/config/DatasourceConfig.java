package com.epam.esm.config;


public interface DatasourceConfig {


    String getDriverClassName();


    String getJdbcUrl();


    String getUsername();


    String getPassword();

    int getMaxPoolSize();

}
