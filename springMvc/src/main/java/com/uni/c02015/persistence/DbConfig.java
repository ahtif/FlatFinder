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
    // jdbc:mysql://host:port/db
    //ds.setUrl("jdbc:mysql://127.0.0.1/springMvc");
    //ds.setUsername("root");
    //ds.setPassword("");
    ds.setUrl("jdbc:mysql://mysql.mcscw3.le.ac.uk:3306/jb603");
    ds.setUsername("jb603");
    ds.setPassword("usburtes");
    return ds;
  }
}
