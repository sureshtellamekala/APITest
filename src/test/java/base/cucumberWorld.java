package base;


import static io.restassured.RestAssured.given;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;


public class cucumberWorld {

  private String apiPath;
  private Response response;

  @Before
  public void setup() {
    RestAssured.baseURI = getBaseURI();
  }
  private String getBaseURI() {
    String baseURI = System.getProperty("baseURI");
    if (StringUtils.isBlank(baseURI)) {
      baseURI = "http://dummy.restapiexample.com/";
    }
    return baseURI;
  }

  public Response makeGETRequest( String apiPath) {
    return given().contentType(ContentType.JSON).log().all().
        when().get(apiPath).
        then().log().all().extract().response();
  }

  public Response makeDELETERequest( String apiPath) {
    return given().contentType(ContentType.JSON).log().all().
        when().delete(apiPath).
        then().log().all().extract().response();
  }

  public String getApiPath() {
      return apiPath;
    }

  public void setApiPath(String apiPath) {
    this.apiPath = apiPath;
  }

  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }
}
