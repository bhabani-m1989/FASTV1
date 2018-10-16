package messages;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PTExceptions {
	
	public static void createPTIssue(String name, String description) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("description", description);
		map.put("name", name);
		map.put("story_type", "bug");

		RestAssured.baseURI = "https://www.pivotaltracker.com";

		Response pt = given().contentType("JSON").header("X-TrackerToken", "d7fe7723f4bbbf4d14e689344f10cb01")
				.header("Content-Type", "application/json").body(map).when()
				.post("/services/v5/projects/2200511/stories").then().statusCode(200).extract().response();

		String resp = pt.asString();
		JsonPath js = new JsonPath(resp);


}
}
