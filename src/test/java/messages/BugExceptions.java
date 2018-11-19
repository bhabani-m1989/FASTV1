package messages;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BugExceptions {
	
	public static void createBugType(String type,String name,String description)
	{
		if(type.equalsIgnoreCase("Jira"))
		{
		createJiraIssue();
		}
		else if(type.equalsIgnoreCase("PT"))
		{
			createPTIssue(name,description);
		}
		
	}
	
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
	
	 public static String getSessionKey()
	 {
	 	  String b= "{\"username\": \"bhabani.m\", \"password\": \"goddezza@123\" }";

	       
	 	  
	 	  RestAssured.baseURI="http://localhost:8090";
	 	  
	 	  Response resp=  given().header("Content-Type","application/json").and().body(b).
	 	  when().post("/rest/auth/1/session").
	 	  then().assertThat().statusCode(200).extract().response();
	 	
	 	    String s =     resp.asString();
	 	    JsonPath js= new JsonPath(s);
	 	    String sessionid= js.get("session.value");
	 	    System.out.println(sessionid);
	 	    return sessionid;
	   }
	 
	 public static void createJiraIssue()
	  {
		  String id= getSessionKey();
		  String line="",res="";
	  try
	  {
			BufferedReader br= new BufferedReader(new FileReader("/Users/bhabanimishra/Desktop/bug.json"));
			line= br.readLine();
			StringBuilder sb= new StringBuilder();
			while(line!= null)
			{
			 sb.append(line);
			 line=br.readLine();
			 
			}
			 res= sb.toString();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  System.out.println(res);
		  
		  RestAssured.baseURI="https://wal-cypress.atlassian.net";
		  
		Response cb=  given().header("content-type","application/json").
		  header("Cookie","JSESSIONID="+id).
		  body(res).
		  when().post("/rest/api/2/issue/").then().assertThat().statusCode(201).extract().response();
		
		System.out.println(cb);
		
		String k=cb.asString();
		JsonPath js= new JsonPath(k);
		String bugid= js.get("id");
		String key =js.get("key");
		System.out.println("The bugid of logged defect is"+bugid);
		System.out.println("the jira key is "+key);
	  }
}
