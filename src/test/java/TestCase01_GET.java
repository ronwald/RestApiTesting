import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class TestCase01_GET {
	
	
	@Test
	void test_01_GET() {
		
		//this is just a simple GET request
		//GET is used to 'get' the state of the resource
		Response myRep = get("https://reqres.in/api/users?page=2");	
		
		System.out.println(myRep.asString());
		System.out.println(myRep.getBody().asString());
		System.out.println(myRep.getStatusCode());
		System.out.println(myRep.getHeader("content-type"));
		
		
		//sample assertion
		int statusCode = myRep.getStatusCode();
		//this should pass
		Assert.assertEquals(statusCode, 200);
	
		
	}
	
	// this will only work if in the import its marked as "static". ex. import static io.restassured.RestAssured.*;
	@Test
	void test_02_GET() {
		
		given()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("data.id[0]", equalTo(7))
			.log().all();
		

		
	}
	
	@SuppressWarnings("unchecked")

	void test_03_POST() {
		//this is a POST test. It needs json library called JSON-simple to be added in the maven dependencies
		//POST creates a resource into the server, using the json body
		//first, create JSONObject
		JSONObject myRequest = new JSONObject();
			
		//add the key-value pair to be in the JSON
		myRequest.put("name", "Ronwald");
		myRequest.put("job", "tester");
		System.out.println(myRequest);
		System.out.println(myRequest.toJSONString());
		
		//now send the POST request using the json request body
		//take note we were expecting status code is 201, since 201 is the common success response for POST create
		//we can also add the header here, it is optional

		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(myRequest.toJSONString()).
		when().
			post("https://reqres.in/api/users").
		then().
			statusCode(201).
			log().all();
		

		
		
	}
	
	
	@SuppressWarnings("unchecked")

	void test_04_PUT() {
		//this is a PUT test. It needs json library called JSON-simple to be added in the maven dependencies
		//PUT requests are for updating a resource. Its same as PATCH. However, when you use PUT, you need to send
		//the whole entity to the server. Once it hit the server, it will replace the existing entity there with the data you sent

		JSONObject myRequest = new JSONObject();		
		//add the key-value pair to be in the JSON
		myRequest.put("name", "Ronwald");
		myRequest.put("job", "student");
		System.out.println(myRequest);
		System.out.println(myRequest.toJSONString());
		
		//now send the POST request using the json request body
		//take note we were expecting status code is 201, since 201 is the common success response for POST create
		//we can also add the header here, it is optional
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(myRequest.toJSONString()).
		when().
			put("https://reqres.in/api/users/2").
		then().
			statusCode(200).
			log().all();
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	
	void test_05_PATCH() {
		//this is a PATCH test. It needs json library called JSON-simple to be added in the maven dependencies
		//PATCH requests are for updating a resource, just like PUT. However, for PATCH, you can directly update
		//data you want to update, and it will not create a new entity in the server

		JSONObject myRequest = new JSONObject();		
		//add the key-value pair to be in the JSON
		//since this is a patch, you can directly put the data to be updated. No need to add anything else
		//for example, if we just want to update the job, then just put it in the body
		myRequest.put("job", "plumber");
		System.out.println(myRequest);
		System.out.println(myRequest.toJSONString());
		
		//now send the POST request using the json request body
		//take note we were expecting status code is 201, since 201 is the common success response for POST create
		//we can also add the header here, it is optional
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(myRequest.toJSONString()).
		when().
			put("https://reqres.in/api/users/2").
		then().
			statusCode(200).
			log().all();
		
		
	}
	
	
	


}
