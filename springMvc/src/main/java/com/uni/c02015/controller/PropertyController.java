package com.uni.c02015.controller;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import com.uni.c02015.SpringMvc;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.PropertyRepository;
import com.uni.c02015.persistence.repository.property.TypeRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PropertyController {

  private static final String IMAGE_ROOT_DIR;
  private static final String POSTCODE_REGEX;

  static {

    IMAGE_ROOT_DIR = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator
            + "webapp" + File.separator + "images"
            + File.separator + "properties" + File.separator;

    POSTCODE_REGEX = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";
  }

  private Pattern postcodePattern = Pattern.compile(POSTCODE_REGEX);

  @Autowired
  TypeRepository typeRepository;
  @Autowired
  LandlordRepository landlordRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  PropertyRepository propertyRepository;

  /**
   * Add a property.
   * @return ModelAndView
   */
  @RequestMapping(value = "/property/add", method = RequestMethod.GET)
  public ModelAndView propertyAdd(HttpServletRequest request) {

    ModelAndView modelAndView = new ModelAndView("property/add");

    modelAndView.addObject("types", typeRepository.findAll());

    // Get the request GET parameters and add to the model
    Map<String, String[]> parameters = request.getParameterMap();
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }

  /**
   * Add property to database.
   * @return String
   */
  @RequestMapping(value = "/property/addPost", method = RequestMethod.POST)
  public String propertyAddPost(HttpServletRequest request, Principal principal,
                                @RequestParam("images") MultipartFile images[]) {

    String query = "";

    // Property number invalid
    if (request.getParameter("pNumber").length() == 0
        || Integer.parseInt(request.getParameter("pNumber")) < 0) {

      query += "invalidNumber=true";
    }

    // Street invalid
    if (request.getParameter("pStreet").length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "streetInvalid=true";
    }

    // City invalid
    if (request.getParameter("pCity").length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "cityInvalid=true";
    }

    // Postcode regex matcher
    Matcher matcher = postcodePattern.matcher(request.getParameter("pPostcode"));

    // Invalid postcode
    if (request.getParameter("pPostcode").length() == 0 || !matcher.find()) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "postcodeInvalid=true";
    }

    // No images supplied
    if (images.length == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "imagesInvalid=true";
    }

    // There was errors in the request
    if (query.length() > 0) {

      // The request was an edit
      if (request.getParameter("edit") != null) {

        return "redirect:/property/edit/" + request.getParameter("edit") + "?" + query;
      }

      // The request was property creation
      return "redirect:/property/add?" + query;
    }

    Property property;

    // Editing an existing property
    if (request.getParameter("edit") != null) {

      property = propertyRepository.findById(new Integer(request.getParameter("edit")));

      // Cannot edit non-existence properties - prompt new property creation
      if (property == null) {

        return "redirect:/property/add";

      // Property exists and all checks were valid
      } else {

        // Delete the property image directory
        try {

          FileUtils.deleteDirectory(new File(IMAGE_ROOT_DIR + property.getId()));

        } catch (IOException e) {

          e.printStackTrace();
        }
      }

    // We are creating a new property
    } else {

      property = new Property();
    }
    
    String propNumber = request.getParameter("pNumber");
    String propStreet = request.getParameter("pStreet");
    String propCity = request.getParameter("pCity");
    String propPostcode = request.getParameter("pPostcode");
    
    property.setNumber(propNumber);
    property.setStreet(propStreet);
    property.setCity(propCity);
    property.setPostcode(propPostcode);
    property.setType(
        typeRepository.findById(new Integer(request.getParameter("pType")))
    );
    property.setRooms(new Integer(request.getParameter("pRooms")));
    
    //Geocode the address of the property to get it's latitude and longitude.
    GeoApiContext context =
        new GeoApiContext().setApiKey("AIzaSyCEawq-gRz787BseZuahn_lFjPfIsTgvj8");
    try {
      GeocodingResult[] results =  GeocodingApi.geocode(context,
          propNumber + " " + propStreet + " " + propCity + " " + propPostcode).await();
      property.setLatitude(results[0].geometry.location.lat);
      property.setLongitude(results[0].geometry.location.lng);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    
    User user = userRepository.findByLogin(((org.springframework.security.core.userdetails.User) 
        ((Authentication) principal).getPrincipal()).getUsername());

    property.setLandlord(landlordRepository.findById(user.getId()));

    propertyRepository.save(property);

    // Save property images
    for (int i = 0; i < images.length; i++) {

      MultipartFile multipartFile = images[i];

      // The file is not empty
      if (!multipartFile.isEmpty()) {

        String fileName = multipartFile.getOriginalFilename();

        byte[] bytes;
        try {

          // Create the directory to store file in
          File dir = new File(IMAGE_ROOT_DIR + property.getId());
          if (!dir.exists()) {

            dir.mkdirs();
          }

          // Create the image file on the server
          File serverFile = new File(dir.getAbsolutePath()
              + File.separator + multipartFile.getOriginalFilename());
          BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
          stream.write(multipartFile.getBytes());
          stream.close();

        } catch (IOException e) {

          e.printStackTrace();
        }
      }
    }

    return "property/addPost";
  }

  /**
   * View all properties.
   * @return ModelAndView
   */
  @RequestMapping(value = "/property/viewAll", method = RequestMethod.GET)
  public ModelAndView viewProperty(Principal principal) {

    ModelAndView modelAndView = new ModelAndView("property/viewAll");

    User user = userRepository.findByLogin(((org.springframework.security.core.userdetails.User) 
        ((Authentication) principal).getPrincipal()).getUsername());
    modelAndView.addObject("properties", 
        propertyRepository.findByLandlord(landlordRepository.findById(user.getId())));

    return modelAndView;
  }
  
  /**
   * View property.
   * @return ModelAndView
   */
  @RequestMapping(value = "/property/view/{id}", method = RequestMethod.GET)
  public ModelAndView viewProperty(@PathVariable(value = "id") Integer id,
                                   Principal principal) {

    ModelAndView modelAndView = new ModelAndView("property/view");

    Property property = propertyRepository.findById(id);

    if (property == null) {

      modelAndView.addObject("notFound", true);

    } else {

      User user = userRepository.findByLogin(((org.springframework.security.core.userdetails.User)
          ((Authentication) principal).getPrincipal()).getUsername());

      // The user owns this property or is an administrator
      if (user.getId() == property.getLandlord().getId()
          || user.getRole().getRole().equals(SpringMvc.ROLE_ADMINISTRATOR)) {

        modelAndView.addObject("showEditButton", id);
      }

      modelAndView.addObject("property", property);

      // Add the absolute image paths to the model
      File rootFolder = new File(IMAGE_ROOT_DIR + id);
      File[] images = rootFolder.listFiles();

      List<String> imagePaths = new ArrayList<String>();
      for (int i = 0; i < images.length; ++i) {

        imagePaths.add(id + File.separator + images[i].getName());
      }

      modelAndView.addObject("images", imagePaths);
    }

    return modelAndView;
  }

  /**
   * Edit a property.
   * @return ModelAndView
   */
  @RequestMapping(value = "/property/edit/{id}", method = RequestMethod.GET)
  public ModelAndView editProperty(@PathVariable(value = "id") Integer id,
                                   HttpServletRequest request, Principal principal) {

    ModelAndView modelAndView = new ModelAndView("property/edit");

    Property property = propertyRepository.findById(id);

    User user = userRepository.findByLogin(((org.springframework.security.core.userdetails.User)
        ((Authentication) principal).getPrincipal()).getUsername());

    // The user is not validated to edit the property
    if (user.getId() != property.getLandlord().getId()
        && !user.getRole().getRole().equals(SpringMvc.ROLE_ADMINISTRATOR)) {

      modelAndView.addObject("invalid", true);

    // Cannot edit non existent properties
    } else if (property == null) {

      modelAndView.addObject("notFound", true);

    // Valid edit
    } else {

      modelAndView.addObject("types", typeRepository.findAll());
      modelAndView.addObject("property", property);

      // Get the request GET parameters and add to the model
      Map<String, String[]> parameters = request.getParameterMap();
      modelAndView.addAllObjects(parameters);
    }

    return modelAndView;
  }
}