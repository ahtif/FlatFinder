package com.uni.c02015;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:build/cucumber-html-report"},
      features = "src/test/resources", glue = "com.uni.c02015")

public class SpringMvcTests {
}
