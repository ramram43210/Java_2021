package com.ram.restassuredtest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UsersWebServiceEndpointTest
{

	private final String CONTEXT_PATH = "/user-app-ws";
	private final String EMAIL_ADDRESS = "arun@yahoo.com";
	private final String JSON = "application/json";
	private static String authorizationHeader;
	private static String userId;

	@BeforeEach
	void setUp() throws Exception
	{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	/*
	 * Test for UserLogin
	 */
	@Test
	final void a()
	{
		Map<String, String> loginDetails = new HashMap<>();
		loginDetails.put("email", EMAIL_ADDRESS);
		loginDetails.put("password", "Pass123");

		Response resposne = given().contentType(JSON).accept(JSON)
				.body(loginDetails).when().post(CONTEXT_PATH + "/login").then()
				.statusCode(200).extract().response();

		authorizationHeader = resposne.header("Authorization");
		userId = resposne.header("UserID");

		assertNotNull(authorizationHeader);
		assertNotNull(userId);

	}

	/*
	 * Test for GetUserDetails
	 */
	@Test
	//@Disabled
	final void b()
	{

		Response response = given().pathParam("id", userId)
				.header("Authorization", authorizationHeader).accept(JSON)
				.when().get(CONTEXT_PATH + "/users/{id}").then().statusCode(200)
				.contentType(JSON).extract().response();

		String userPublicId = response.jsonPath().getString("userId");
		String userEmail = response.jsonPath().getString("email");
		String firstName = response.jsonPath().getString("firstName");
		String lastName = response.jsonPath().getString("lastName");
		List<Map<String, String>> addresses = response.jsonPath().getList("addresses");
		String addressId = addresses.get(0).get("addressId");

		assertNotNull(userPublicId);
		assertNotNull(userEmail);
		assertNotNull(firstName);
		assertNotNull(lastName);
		assertEquals(EMAIL_ADDRESS, userEmail);

		assertTrue(addresses.size() == 2);
		assertTrue(addressId.length() == 30);

	}

	/*
	 * Test for updateUserDetails
	 */
	@Test
	//@Disabled
	final void c()
	{
		Map<String, Object> userDetails = new HashMap<>();
		userDetails.put("firstName", "Peter");
		userDetails.put("lastName", "John");
		userDetails.put("email", "Peter@yahoo.com");
		userDetails.put("password", "Pass123");
		
		List<Map<String, Object>> userAddresses = new ArrayList<>();
		
		Map<String, Object> billingAddress = new HashMap<>();
		billingAddress.put("city", "Chennai");
		billingAddress.put("country", "India");
		billingAddress.put("streetName", "999 Street name");
		billingAddress.put("postalCode", "888888");
		billingAddress.put("type", "billing");
		
		userAddresses.add(billingAddress);

		Map<String, Object> shippingAddress = new HashMap<>();
		shippingAddress.put("city", "Bangalore");
		shippingAddress.put("country", "India");
		shippingAddress.put("streetName", "123 Street name");
		shippingAddress.put("postalCode", "123456");
		shippingAddress.put("type", "shipping");
		
		userAddresses.add(shippingAddress);

		userDetails.put("addresses", userAddresses);

		Response response = given().contentType(JSON).accept(JSON)
				.header("Authorization", authorizationHeader)
				.pathParam("id", userId).body(userDetails).when()
				.put(CONTEXT_PATH + "/users/{id}").then().statusCode(200)
				.contentType(JSON).extract().response();

		String firstName = response.jsonPath().getString("firstName");
		String lastName = response.jsonPath().getString("lastName");

		List<Map<String, String>> storedAddresses = response.jsonPath()
				.getList("addresses");

		assertEquals("Peter", firstName);
		assertEquals("John", lastName);
		assertNotNull(storedAddresses);
		assertTrue(storedAddresses.size() == 2);
		
	}

	/*
	 * Test for DeleteUserDetails
	 */	
	
	@Test
	@Disabled
	final void d()
	{
		Response response = given().header("Authorization", authorizationHeader)
				.accept(JSON).pathParam("id", userId).when()
				.delete(CONTEXT_PATH + "/users/{id}").then().statusCode(200)
				.contentType(JSON).extract().response();

		String operationResult = response.jsonPath().getString("operationResult");
		assertEquals("SUCCESS", operationResult);

	}

}
