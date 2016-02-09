package com.uni.c02015.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConfig {

  /**
   * JPA mysql connection details.
   * @return DriverManagerDataSource
   */
  @Bean
  public DriverManagerDataSource dataSource() {

    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://127.0.0.1/springMvc");
    ds.setUsername("root");
    ds.setPassword("");
    return ds;
  }
}