package com.uni.c02015;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  /**
   * Add resource handlers.
   * @param registry ResourceHandlerRegistry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
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