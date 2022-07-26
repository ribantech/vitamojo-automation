package Runner;

import java.util.Random;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Serializer.Login;
import Serializer.Profile;
import Serializer.Signup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiStepDefs {
	Response response;
	String baseUri = "https://vmos2.vmos-demo.com";
    String signupPath="/user/v1/user";
    String loginPath = "/user/v1/auth";
    String tenant = "695a1486-80e7-4ee6-bc55-f4911944ef2a";
    RequestSpecBuilder reqBuilder;
    RequestSpecification reqSpec;
    
	@Given("Valid endpoint with payload to login user")
	public void valid_endpoint_with_payload_to_login_user() {
	    
	    reqBuilder = new RequestSpecBuilder();
	 	reqBuilder.setBaseUri(baseUri);
	 	reqBuilder.setBasePath(loginPath);
	 	reqSpec = reqBuilder.build();
	}
	
	
	@When("Login with {string} and {string}")
	public void payload_with_and_is_send_to_the_server(String email, String password) {
		Login login = new Login(email, password);
		ObjectMapper mapper = new ObjectMapper();
	    String payload = null;
	    try {
	         payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(login);
	    } catch (JsonProcessingException e) {
	         e.printStackTrace();
	    }
	    
	     
	    response = RestAssured.given()
	    		.spec(reqSpec)
	    	    .contentType(ContentType.JSON)
	    	    .body(payload)   
	    	    .when()
	    	    .post()
	    	    .then()
	    	    .contentType(ContentType.JSON).
	    	    extract().response();
	}
	
	@Then("User successfully logs in")
	public void user_logs_in_with_name_as_username1() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201, "User successfully logs in");
	}
	
	@Then("User login fails")
	public void user_login_fails() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400, "User log in fails");
	}
	
	//Sign up Step definitions
	
	@Given("Valid endpoint with payload to signup user")
	public void valid_endpoint_with_payload_to_signup_user() {
	    
	    reqBuilder = new RequestSpecBuilder();
	 	reqBuilder.setBaseUri(baseUri);
	 	reqBuilder.setBasePath(signupPath);
	 	reqSpec = reqBuilder.build();
	}
	

	@When("Sign up with {string}, {string} and {string}")
	public void payload_with_and_is_send_to_the_server(String name, String email, String password) {
		Profile profile = new Profile(name);
		String locale = "en-GB";
		String storeid = "ced48917-54c2-40ad-a646-5315f5dcb28f";
		Random rand = new Random();	  
        int num = 1000+rand.nextInt(1000);
        email = num+email;
		Signup signup = new Signup(email, password, locale, storeid, profile);
		
		// Converting a Java class object to a JSON payload as string
        ObjectMapper mapper = new ObjectMapper();
        String payload = null;
        try {
            payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(signup);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	    
	    response = RestAssured.given()
	    		.spec(reqSpec)
	    	    .contentType(ContentType.JSON)
	    	    .header("tenant", tenant)
	    	    .body(payload)   
	    	    .when()
	    	    .post()
	    	    .then()
	    	    .contentType(ContentType.JSON).
	    	    extract().response();
	}
	
	@Then("User successfully signs up")
	public void user_successfully_signs_up() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201, "User successfully signs up");
	}
	
	@Then("Sign up fails")
	public void sign_up_fails() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400, "User sign up fails");
	}


}
