package com.uni.c02015;

import com.uni.c02015.controller.interceptor.NotificationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  /**
   * Create a notification interceptor bean - Spring will manage the object lifecycle!
   * @return
   */
  @Bean
  public NotificationInterceptor notificationInterceptor() {

    return new NotificationInterceptor();
  }

  /**
   * Add interceptors.
   * @param registry InterceptorRegistry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(notificationInterceptor());
  }

  /**
   * Add resource handlers.
   * @param registry ResourceHandlerRegistry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    // Reference resources through an absolute directory path
    registry.addResourceHandler("/resources/**").addResourceLocations(
        "file://" + System.getProperty("user.dir") + File.separator + "src" + File.separator
        + "main" + File.separator + "webapp" + File.separator
    );
  }

  /**
   * Equivalent of web.xml <mvc:default-servlet-handler/> tag
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    configurer.enable();
  }

  /**
   * View resolver.
   * @return InternalResourceViewResolver
   */
  @Bean
  public InternalResourceViewResolver viewResolver() {

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setOrder(2);
    return viewResolver;
  }
}