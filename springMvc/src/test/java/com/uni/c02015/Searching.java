package com.uni.c02015;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.servlet.Filter;

@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvc.class, SecurityConfig.class, WebConfig.class})
public class Searching {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private Filter springSecurityFilterChain;
  @Autowired
  private UserRepository userRepository;

  private MockMvc mockMvc;
  private ResultActions result;
  private Authentication authentication;
  private User user;

  /**
   * Ran before every scenario.
   */
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .addFilters(springSecurityFilterChain)
            .apply(springSecurity())
            .build();
  }

  @Given("^I am logged in as searcher \"([^\"]*)\"$")
  public void iam_logged_in_as_searcher(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I enter a postcode \"([^\"]*)\"$")
  public void ienter_a_postcode(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I specify a distance of \"([^\"]*)\" mile radius$")
  public void ispecify_a_distance_of_mile_radius(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^only properties within (\\d+) miles should show$")
  public void onlyproperties_within_miles_should_show(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I filter by the price \"([^\"]*)\" or lower \\(PCM\\)$")
  public void ifilter_by_the_price_or_lower_Pcm(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should only be able to see properties that cost (\\d+) or less$")
  public void ishould_only_be_able_to_see_properties_that_cost_or_less(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am logged in as a searcher \"([^\"]*)\"$")
  public void iam_logged_in_as_a_searcher(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I filter by \"([^\"]*)\" bedrooms$")
  public void ifilter_by_bedrooms(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^only property with (\\d+) Bedrooms should be displayed$")
  public void onlyproperty_with_Bedrooms_should_be_displayed(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I filter by postcode \"([^\"]*)\"$")
  public void ifilter_by_postcode(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I filter by radius \\(miles\\) \"([^\"]*)\"$")
  public void ifilter_by_radius_miles(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I set the price to (\\d+)$")
  public void iset_the_price_to(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I filter listings by start date \"([^\"]*)\"$")
  public void ifilter_listings_by_start_date(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I set the end date \"([^\"]*)\"$")
  public void iset_the_end_date(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to only see properties available from \"([^\"]*)\" to \"([^\"]*)\"$")
  public void ishould_be_able_to_only_see_properties_available_from_to(String arg1, String arg2)
          throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^only (\\d+) bed room properties within (\\d+) miles of \"([^\"]*)\" that cost (\\d+) "
          + "pounds PCM should show$")
  public void only_bed_room_properties_within_miles_of_that_cost_pounds_Pcm_should_show(
          int arg1, int arg2, String arg3, int arg4) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

}
