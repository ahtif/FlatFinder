package com.uni.c02015.controller;

import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.DbConfig;
import com.uni.c02015.persistence.repository.property.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SearchController {

  // Private connection for search queries - non overloading with hibernate
  private static Connection DB_CONN;

  static {

    try {

      // Create another database connection for search queries - reduce load as is read only connection
      Class.forName("com.mysql.jdbc.Driver");
      DB_CONN = DriverManager.getConnection(
          "jdbc:mysql://" + DbConfig.HOST + "/"
              + DbConfig.DATABASE, DbConfig.USER, DbConfig.PASSWORD
      );

    } catch (SQLException e) {

      e.printStackTrace();

    } catch (ClassNotFoundException e) {

      e.printStackTrace();
    }
  }

  @Autowired
  private PropertyRepository propertyRepository;

  /**
   * Search results view.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping(value = "/searchProperties", method = RequestMethod.GET)
  public ModelAndView searchQuery(HttpServletRequest request) {

    ModelAndView modelAndView = new ModelAndView("search/index");

    // Get search criteria
    String keyword = request.getParameter("pKeyword");
    String type = request.getParameter("pType");
    String rooms = request.getParameter("pRooms");
    String minPricePerMonth = request.getParameter("pMinPPM");
    String maxPricePerMonth = request.getParameter("pMaxPPM");

    // Not all of the form was submitted
    if (keyword.length() == 0) {

      modelAndView.addObject("error", true);

    // All of the form was submitted
    } else {

      // Create the mysql query
      String query = "SELECT id FROM property WHERE (city LIKE ? OR postcode LIKE ? "
          + "OR street LIKE ?)";
      List<Property> properties = new LinkedList<Property>();

      if (!rooms.equals("-1")) {

        query += " AND rooms = " + rooms;
      }

      if (!type.equals("-1")) {

        query += " AND type = " + type;
      }

      if (minPricePerMonth.length() > 0) {

        query += " AND price_per_month >= " + minPricePerMonth;
      }

      if (maxPricePerMonth.length() > 0) {

        query += " AND price_per_month <= " + maxPricePerMonth;
      }

      if (request.getParameter("pValidFrom").length() > 0) {

        query += " AND valid_from >= '" + request.getParameter("pValidFrom") + "'";
      }

      if (request.getParameter("pValidTo").length() > 0) {

        query += " AND valid_to <= '" + request.getParameter("pValidTo") + "'";
      }

      try {

        PreparedStatement preparedStatement = DB_CONN.prepareStatement(query);

        preparedStatement.setString(1, "%" + keyword + "%");
        preparedStatement.setString(2, "%" + keyword + "%");
        preparedStatement.setString(3, "%" + keyword + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        // Get property objects from the result set
        while (resultSet.next()) {

          properties.add(propertyRepository.findById(resultSet.getInt("id")));
        }

      } catch (SQLException e) {

        e.printStackTrace();
      }

      // No properties found
      if (properties.isEmpty()) {

        modelAndView.addObject("noResults", true);

      // Properties were found
      } else {

        modelAndView.addObject("properties", properties);
      }
    }

    return modelAndView;
  }
}