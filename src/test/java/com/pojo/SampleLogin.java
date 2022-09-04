
package com.pojo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class SampleLogin extends BaseClass {

	String logtoken;
	String address_id;

	
	@Test(priority = 6)
	public void changeProfilePic() {
		// 1.Headers

				List<Header> listHeaders = new ArrayList<>();

				Header h1 = new Header("accept", "application/json");
				Header h2 = new Header("Authorization", "Bearer " + logtoken);
				Header h3 = new Header("Content-Type", "multipart/form-data");

				listHeaders.add(h1);
				listHeaders.add(h2);
				listHeaders.add(h3);

				Headers headers = new Headers(listHeaders);
				addHeaders(headers);
				
				//pass the payload as from date
				
				Response response = requestType("POST", "https://omrbranch.com/api/changeProfilePic");
				
				int statusCode = getStatusCode(response);
				Assert.assertEquals(statusCode, 200, "verify status code");
				System.out.println(statusCode);



	}
	
	
	
	
	
	
	
	@Test(priority = 5)
	private void getUserAddress() {
		
		// 1.Headers

				List<Header> listHeaders = new ArrayList<>();

				Header h1 = new Header("accept", "application/json");
				Header h2 = new Header("Authorization", "Bearer " + logtoken);
		
				listHeaders.add(h1);
				listHeaders.add(h2);
				

				Headers headers = new Headers(listHeaders);
				addHeaders(headers);
		
				// 3.Request Type

				Response response = requestType("GET", "https://omrbranch.com/api/getUserAddress");
				
				int statusCode = getStatusCode(response);
				Assert.assertEquals(statusCode, 200, "verify status code");
				System.out.println("statusCode");

		
		

	}
	
	@Test(priority = 4)
	public void deleteUserAddress() {

		// 1.Headers

		List<Header> listHeaders = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

		// 2.pass the payload
		
		DeleteUserAddress_Input_Pojo address_Input_Pojo = new DeleteUserAddress_Input_Pojo(address_id);
		addBody(address_Input_Pojo);

		// 3.Request Type

		Response response = requestType("DELETE", "https://omrbranch.com/api/deleteAddress");

		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify status code");

		DeleteUserAddress_Output_Pojo as = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = as.getMessage();
		System.out.println(message);
	}

	@Test(priority = 3)
	public void updateUserAddress() {

		// 1.Headers

		List<Header> listHeaders = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

		// 2.pass the payload

		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_id,
				"Santhosh", "Kumar", "8939276784", "NGK", 31, 3231, 107, "600017", "Chennai", "home");

		addBody(updateUserAddress_Input_Pojo);

		// 3.Request Type

		Response response = requestType("PUT", "https://omrbranch.com/api/updateUserAddress");

		// AddUserAddress_Output_Pojo as =
		// response.as(AddUserAddress_Output_Pojo.class);

		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify status code");

		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String message = updateUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Address updated successfully", "verify Address updated successfully");

	}

	@Test(priority = 2)
	public void addUserAddress() {

		// 1.Headers

		List<Header> listHeaders = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

		// 2.pass the payload

		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("G K", "SUGUMAR",
				"8939282410", "apartment", 33, 3378, 101, "609887", "64/63 partap nagar", "home");

		addBody(addUserAddress_Input_Pojo);

		// 3.RequestType

		Response response = requestType("POST", "https://omrbranch.com/api/addUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "verify status code");
		System.out.println(getResBodyAsPrettyString(response));

		AddUserAddress_Output_Pojo address_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String message = address_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Address added successfully", "verify Address added successfully");

		int id = address_Output_Pojo.getAddress_id();
		address_id = String.valueOf(id);

		// System.out.println("My Address Id Is:"+address_id);
	}

	@Test(priority = 1)
	public void login() {

		// 1.Header

		addHeader("accept", "application/json");
		// 2.Basic Authentication

		basicAuth("suguzylikesu@gmail.com", "Sandy@123");

		// 3.Request Type

		Response response = requestType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");

		int statusCode = getStatusCode(response);
		Assert.assertEquals(200, statusCode, "verify status code");

		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);

		Login_Output_Pojo as = response.as(Login_Output_Pojo.class);

		System.out.println(as);

		Data data = as.getData();

		String first_name = data.getFirst_name();
		Assert.assertEquals(first_name, "G K", "verifying first name");
		System.out.println(first_name);

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String message = login_Output_Pojo.getMessage();
		Assert.assertEquals(message, "Login successfully", "verify Login successfully");
		logtoken = login_Output_Pojo.getData().getLogtoken();

	}

}
