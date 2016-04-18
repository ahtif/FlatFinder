package com.uni.c02015.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConfig {

  public static final String HOST;
  public static final String DATABASE;
  public static final String USER;
  public static final String PASSWORD;

  static {

    HOST = "127.0.0.1";
    DATABASE = "springMvc";
    USER = "root";
    PASSWORD = "";
  }

  /**
   * JPA mysql connection details.
   * @return DriverManagerDataSource
   */
  @Bean
  public DriverManagerDataSource dataSource() {

    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://mysql.mcscw3.le.ac.uk:3306/hcz1");
    ds.setUsername("hcz1");
    ds.setPassword("emetorne");
    return ds;
  }
}
