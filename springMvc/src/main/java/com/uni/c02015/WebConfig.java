package com.uni.c02015;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  /**
   * Add resource handlers.
   * @param registry ResourceHandlerRegistry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    // Reference resources through an absolute directory path
    registry.addResourceHandler("/resources/**").addResourceLocations(
        "file://" + System.getProperty("user.dir") + File.separator + "src" + File.separator
        + "main" + File.separator + "resources" + File.separator
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