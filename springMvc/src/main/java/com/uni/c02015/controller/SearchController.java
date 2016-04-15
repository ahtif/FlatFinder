package com.uni.c02015.controller;

import com.uni.c02015.persistence.DbConfig;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
public class SearchController {

  // Private connection for search queries - non overloading with hibernate
  private static Connection DB_CONN;

  static {

    try {

      Class.forName("com.mysql.jdbc.Driver");
      DB_CONN = DriverManager.getConnection("jdbc:mysql://" + DbConfig.HOST + "/" + DbConfig.DATABASE, DbConfig.USER, DbConfig.PASSWORD);

    } catch (SQLException e) {

      e.printStackTrace();

    } catch (ClassNotFoundException e) {

      e.printStackTrace();
    }
  }

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SearcherRepository searcherRepository;
  @Autowired
  private LandlordRepository landlordRepository;

  @RequestMapping(value = "/searchProperties", method = RequestMethod.GET)
  public ModelAndView searchQuery(HttpServletRequest request) {


    Statement stmt = null;
    try {

      stmt = DB_CONN.createStatement();

    } catch (SQLException e) {

      e.printStackTrace();
    }

    // TODO Build the search query
    String query = "";

    try {

      ResultSet rs = stmt.executeQuery(query);

    } catch (SQLException e) {

      e.printStackTrace();
    }

    ModelAndView modelAndView = new ModelAndView("search/index");

    return modelAndView;
  }
}