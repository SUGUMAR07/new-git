package com.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	RequestSpecification reqspec;
	Response response;
	
	public void formData() {
		reqspec.multiPart("profile_picture",new File("C:\\Users\\HP\\Pictures\\Saved Pictures"));

	}

	public void addHeader(String key, String value) {
		reqspec = RestAssured.given().header(key, value);

	}

	public void queryParam(String key, String value) {
		reqspec = reqspec.queryParam(key, value);

	}

	public void pathParam(String key, String value) {
		reqspec = reqspec.pathParam(key, value);

	}

	public void addBody(String body) {
		reqspec = reqspec.body(body);

	}

	public void addBody(Object body) {
		reqspec = reqspec.body(body);

	}

	public Response requestType(String type, String endpoint) {

		switch (type) {
		case "GET":

			response = reqspec.log().all().get(endpoint);

			break;

		case "PUT":

			response = reqspec.log().all().put(endpoint);

			break;

		case "POST":

			response = reqspec.log().all().post(endpoint);

			break;

		case "DELETE":

			response = reqspec.log().all().delete(endpoint);

			break;

		default:
			break;
		}
		return response;

	}

	public int getStatusCode(Response response) {

		int statusCode = response.getStatusCode();
		return statusCode;

	}

	public String asResBodyAsString(Response response) {
		String asString = response.asString();
		return asString;

	}

	public String getResBodyAsPrettyString(Response response) {

		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}

	public void basicAuth(String username, String password) {

		reqspec = reqspec.auth().preemptive().basic(username, password);

	}

//MORE THAN ONE HEADERS

	public void addHeaders(Headers headers) {

		//List<Header> header = new ArrayList<>();

		// Header h1 = new Header("key", "value");
		// Header h2 = new Header("key", "value");
		// Header h3 = new Header("key", "value");

		// header.add(h1);
		// header.add(h2);
		// header.add(h3);

		// Headers headers = new Headers(header);
		reqspec = RestAssured.given().headers(headers);

	}
}
