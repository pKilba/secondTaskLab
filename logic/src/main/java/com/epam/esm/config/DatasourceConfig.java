package com.epam.esm.config;


public interface DatasourceConfig {


    /**
     * @return driver class name
     */
    String getDriverClassName();


    /**
     *
     * @return jdbc url
     */
    String getJdbcUrl();


    /**
     *
     * @return user name
     */
    String getUsername();


    /**
     *
     * @return password
     */
    String getPassword();

    /**
     *
     * @return max pool size
     */
    int getMaxPoolSize();

}
