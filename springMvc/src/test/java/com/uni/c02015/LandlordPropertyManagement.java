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
public class LandlordPropertyManagement {

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

  @Given("^I am a landlord \"([^\"]*)\"$")
  public void iamalandlord(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am on the update property page$")
  public void iamonthe_update_property_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I select property (\\d+) Aylestone Rd to update$")
  public void iselect_property_Aylestone_Rd_to_update(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to upload img(\\d+)\\.png to give a further description of my property$")
  public void ishould_be_able_to_upload_img_png_to_give_a_further_description_of_my_property(
      int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I select property (\\d+) London Rd to update$")
  public void iselect_property_London_Rd_to_update(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I select property (\\d+) Welford Rd to update$")
  public void iselectproperty_Welford_Rd_to_update(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the address <ADDRESS>$")
  public void ihaveentered_the_address_address() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the postcode LE(\\d+) (\\d+)LG$")
  public void ihaveentered_the_postcode_le_lg(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the no\\. bedrooms (\\d+)$")
  public void ihaveentered_the_no_bedrooms(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the price (\\d+)$")
  public void ihaveentered_the_price(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the size (\\d+) sq\\. metres$")
  public void ihaveentered_the_size_sq_metres(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the start date (\\d+)/(\\d+)/(\\d+)$")
  public void ihaveentered_the_start_date(int arg1, int arg2, int arg3) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the end date <ENDATE$")
  public void ihaveentered_the_end_date_Enddate() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the local information Urban Area- Victorian Housing$")
  public void ihaveentered_the_local_information_Urban_area_victorian_housing() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have attached the image AyleStone\\.png$")
  public void ihaveattached_the_image_AyleStone_png() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I submit the form$")
  public void isubmit_the_form() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^the property should be listed on the system$")
  public void theproperty_should_be_listed_on_the_system() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the postcode LE(\\d+) (\\d+)LG$")
  public void withthe_postcode_le_lg(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the no\\. bedrooms (\\d+)$")
  public void withthe_no_bedrooms(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the price (\\d+)$")
  public void withthe_price(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the size (\\d+) sq\\. metres$")
  public void withthe_size_sq_metres(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the start date (\\d+)/(\\d+)/(\\d+)$")
  public void withthe_start_date(int arg1, int arg2, int arg3) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the end date <ENDATE$")
  public void withthe_end_date_endate() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the local information Urban Area- Victorian Housing$")
  public void withthe_local_information_Urban_Area_Victorian_Housing() throws Throwable {
    // Write code here that turns the phrase above into concrete action
  }

  @Given("^I have entered the postcode LE(\\d+) (\\d+)BE$")
  public void ihaveentered_the_postcode_le_be(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the local information Urban Area- City Centre$")
  public void ihaveentered_the_local_information_urban_area_city_centre() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have attached the image LndRd\\.png$")
  public void ihaveattached_the_image_Lndrd_png() throws Throwable {
    // Write code here that turns the phrase above into concrete action
  }

  @Then("^with the postcode LE(\\d+) (\\d+)BE$")
  public void withthe_postcode_le_be(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the local information Urban Area- City Centre$")
  public void withthe_local_information_urban_area_city_centre() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the postcode LE(\\d+) (\\d+)EG$")
  public void ihaveentered_the_postcode_le_eg(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have entered the local information Urban Area$")
  public void ihaveentered_the_local_information_urban_Area() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I have attached the image Welford\\.png$")
  public void ihaveattached_the_image_Welford_png() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the postcode LE(\\d+) (\\d+)EG$")
  public void withthe_postcode_le_eg(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^with the local information Urban Area$")
  public void withthe_local_information_urban_Area() throws Throwable {
    // Write code here that turns the phrase above into concrete action
  }
}
