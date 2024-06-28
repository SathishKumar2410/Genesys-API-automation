package Tests;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Reusables.BaseClass;
import io.restassured.response.Response;

public class RunnerClass {

	String contentType = "application/json";

	@Test(priority = 1)
	public void postMessage() {

		String endPoint = "api/messages";
		String requestBody = "{\"from\":{\"id\":\"fromUserId\"},\"to\":{\"id\":\"toUserId\"},\"message\":\"message text\"}";
		String expectedResponseBody = "{\"from\":{\"id\":\"fromUserId\"},\"to\":{\"id\":\"toUserId\"},\"message\":\"message text\"}";
		int expectedStatusCode = 200;
		
		Response response = BaseClass.POST(endPoint, requestBody, contentType);
		System.out.println(response.asPrettyString());
		BaseClass.compareResponseAndStatusCodeForJsonObject(response, expectedStatusCode, expectedResponseBody);
	}

	@Test(priority = 2)
	public void getMessage() {
		HashMap<String, String> pathParams = new HashMap<String, String>();
		HashMap<String, String> queryParams = new HashMap<String, String>();

		String endPoint = "api/messages/{id}";
		JSONObject jsonbody = new JSONObject(createMessage().asString());
		String pathParameter = jsonbody.getString("id");
		pathParams.put("id", pathParameter);
		String expectedResponseBody = "{\"from\":{\"id\":\"fromUserId\"},\"to\":{\"id\":\"toUserId\"},\"message\":\"message text\"}";
		int expectedStatusCode = 200;
		
		Response response = BaseClass.GET(endPoint, pathParams, queryParams);
		System.out.println(response.asPrettyString());
		BaseClass.compareResponseAndStatusCodeForJsonObject(response, expectedStatusCode, expectedResponseBody);

	}

	@Test(priority = 3)
	public void getListMessage() {
		HashMap<String, String> pathParams = new HashMap<String, String>();
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("from", "fromUserId");
		queryParams.put("to", "toUserId");

		String endPoint = "api/messages";
		int expectedStatusCode = 200;
		JSONObject jsonbody = new JSONObject(createMessage().asString());
		String messageId = jsonbody.getString("id");
		String message = jsonbody.getString("message");
		
		Response response = BaseClass.GET(endPoint, pathParams, queryParams);
		System.out.println(response.asPrettyString());
		BaseClass.compareResponseAndStatusCodeForJsonArray(response, expectedStatusCode, messageId, message);

	}

	@Test(priority = 4)
	public void deleteMessage() {

		HashMap<String, String> pathParams = new HashMap<String, String>();

		String endPoint = "api/messages/{id}";
		JSONObject jsonbody = new JSONObject(createMessage().asString());
		String pathParameter = jsonbody.getString("id");
		pathParams.put("id", pathParameter);
		int expectedStatusCode = 204;
		
		Response response = BaseClass.DELETE(endPoint, pathParams);
		System.out.println(response.asPrettyString());
		Assert.assertTrue(response.statusCode() == expectedStatusCode, "Message deleted successfully.");
	}

	@Test(priority = 5)
	public void postMessageWithInvalidRequestBody() {

		String endPoint = "api/messages";
		String requestBody = "{}";
		int expectedStatusCode = 400;
		
		Response response = BaseClass.POST(endPoint, requestBody, contentType);
		System.out.println(response.asPrettyString());
		Assert.assertTrue(response.statusCode() == expectedStatusCode, "Invalid request body response code verified.");

	}

	@Test(priority = 6)
	public void getMessageWithInvalidMessageId() {
		HashMap<String, String> pathParams = new HashMap<String, String>();
		HashMap<String, String> queryParams = new HashMap<String, String>();

		String endPoint = "api/messages/{id}";
		pathParams.put("id", "12345");
		int expectedStatusCode = 404;
		
		Response response = BaseClass.GET(endPoint, pathParams, queryParams);
		Assert.assertTrue(response.statusCode() == expectedStatusCode, "Invalid messageId response code verified.");

	}

	@Test(priority = 7)
	public void deleteMessageWithInvalidMessageId() {
		HashMap<String, String> pathParams = new HashMap<String, String>();

		String endPoint = "api/messages/{id}";
		pathParams.put("id", "12345");
		int expectedStatusCode = 404;
		Response response = BaseClass.DELETE(endPoint, pathParams);
		Assert.assertTrue(response.statusCode() == expectedStatusCode, "Invalid messageId response code verified.");

	}

	public Response createMessage() {
		String endPoint = "api/messages";
		String requestBody = "{\"from\":{\"id\":\"fromUserId\"},\"to\":{\"id\":\"toUserId\"},\"message\":\"message text\"}";
		Response response = BaseClass.POST(endPoint, requestBody, contentType);
		if (response.statusCode() == 200) {

			System.out.println(response.asPrettyString());

			return response;
		}

		return null;
	}

}
