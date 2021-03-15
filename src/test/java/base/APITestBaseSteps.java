package base;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;
import java.util.Map;

public class APITestBaseSteps {

  private cucumberWorld cucumberWorld;
  private Response response;

  public APITestBaseSteps(cucumberWorld cucumberWorld) {
    this.cucumberWorld = cucumberWorld;
  }

  @Given("^an endpoint \"([^\"]*)\"$")
  public void endpoint(String endpoint) {
    cucumberWorld.setApiPath(endpoint);
  }

  @When("^I make a GET request to the API$")
  public void createGetRequest() {
    response = cucumberWorld.makeGETRequest(cucumberWorld.getApiPath());
    cucumberWorld.setResponse(response);
  }

  @Then("^I should receive HTTP status code (\\d+)$")
  public void checkHTTPStatusCode(int statusCode) {
    cucumberWorld.getResponse().then().statusCode(statusCode);
  }

  @And("^I add path params to the URL$")
  public void addParamsToURL(Map<String, String> pathParams) {
    StringBuffer path = new StringBuffer(cucumberWorld.getApiPath());
    for (Map.Entry<String, String> entry : pathParams.entrySet()) {
      path.append("/" + entry.getValue());
    }
    cucumberWorld.setApiPath(path.toString());
    System.out.println("API PATH:: " + cucumberWorld.getApiPath());
  }

  @When("^I make a DELETE request to the API$")
  public void createDeleteRequest() {
    response = cucumberWorld.makeDELETERequest(cucumberWorld.getApiPath());
    cucumberWorld.setResponse(response);
  }
  @And("^the response field will contain the value$")
  public void checkResponseValues(Map<String, String> fieldValue) {
    for (Map.Entry<String, String> entry : fieldValue.entrySet()) {
      try {
        String responseValue = cucumberWorld.getResponse().then().extract().path(entry.getKey())
            .toString();
        assertEquals(entry.getValue(), responseValue);
      } catch (NullPointerException e) {
        throw new NullPointerException("Field value " + entry + " is not present in response");
      }
    }
  }
}
