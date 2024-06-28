package Reusables;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseClass {

	private static String baseURI = "http://localhost:3000/";

	public static void compareResponseAndStatusCodeForJsonObject(Response response, int expectedStatusCode,
			String expectedResponseBody) {
		int actualStatusCode = response.getStatusCode();
		JSONObject json = new JSONObject(response.asPrettyString());
		json.remove("time");
		json.remove("id");
		if (actualStatusCode == expectedStatusCode) {
			Assert.assertEquals(json.toString(), expectedResponseBody,
					"Actual response body does not matches the Expected response body.");
			System.out.println(response.toString());
		} else
			Assert.fail("Actual status code " + actualStatusCode + "does not matches the Expected status code "
					+ expectedStatusCode);

	}

	public static void compareResponseAndStatusCodeForJsonArray(Response response, int expectedStatusCode,
			String messageId, String message) {
		int actualStatusCode = response.getStatusCode();
		JSONArray jsonArray = new JSONArray(response.asPrettyString());
		if (actualStatusCode == expectedStatusCode) {
			for (Object json : jsonArray) {
				JSONObject jsonObject = new JSONObject(json.toString());
				if (jsonObject.get("id").toString().equals(messageId)
						&& jsonObject.get("message").toString().equals(message)) {
					Assert.assertTrue(true, "Message list contains the expected messageId and message.");
					System.out.println(response.toString());
				}
			}
		} else
			Assert.fail("Actual status code " + actualStatusCode + "does not matches the Expected status code "
					+ expectedStatusCode);
	}

	public static Response GET(String endPoint, HashMap<String, String> pathParams,
			HashMap<String, String> queryParams) {
		Response response = RestAssured.given().pathParams(pathParams).queryParams(queryParams).baseUri(baseURI)
				.get(endPoint);
		return response;
	}

	public static Response POST(String endPoint, String requestBody, String contentType) {
		Response response = RestAssured.given().baseUri(baseURI).body(requestBody).contentType(contentType)
				.post(endPoint);
		return response;
	}

	public static Response DELETE(String endPoint, HashMap<String, String> pathParams) {
		Response response = RestAssured.given().pathParams(pathParams).baseUri(baseURI).delete(endPoint);
		return response;
	}

}
