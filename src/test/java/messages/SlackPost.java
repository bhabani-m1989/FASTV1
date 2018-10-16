package messages;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SlackPost {
	
	public static Map<String,Object> createSlackMessage(int pcount,int fcount,int scount)
	{
		Map<String,Object>outer= new HashMap<String,Object>();
		
		
		List<Object> attachments= new ArrayList<Object>();
		
		Map<String,Object>data= new HashMap<String,Object>();
		data.put("fallback", "Required plain-text summary of the attachment.");
		data.put("color", "#2eb886");
		data.put("pretext", "Automation Test Execution");
		data.put("author_name", "Executed By : Bhabani Mishra");
		data.put("title", "Slack Test Summary");
		data.put("author_icon", "/Users/bhabanimishra/Documents/workspace/DockImg/westagilelabs.jpg");
		data.put("title_link", "https://api.slack.com/");
		data.put("text", "Project Name: Socio");
		
		List<Object> fields= new ArrayList<Object>();
		
		Map<String,Object>inner_data_one= new HashMap<String,Object>();
		inner_data_one.put("title","Pass TC_Count:");
		inner_data_one.put("value",pcount+"");
		
		Map<String,Object>inner_data_two= new HashMap<String,Object>();
		inner_data_two.put("title","Failed TC_Count:");
		inner_data_two.put("value",fcount+"");
		
		Map<String,Object>inner_data_three= new HashMap<String,Object>();
		inner_data_three.put("title","Skipped TC_Count:");
		inner_data_three.put("value",scount+"");
		
		fields.add(inner_data_one);
		fields.add(inner_data_two);
		fields.add(inner_data_three);
		
		
		
		
		data.put("fields",fields);
		data.put("footer", "Slack API");
		data.put("footer_icon", "https://platform.slack-edge.com/img/default_application_icon.png");
		
		attachments.add(data);
		
		outer.put("attachments",attachments);
		
		return outer;
	}
	
	public static String  createJsonString(int pass_count,int fail_count,int skip_count)
	{
		String json="";
		try
		{
		 json = new ObjectMapper().writeValueAsString(createSlackMessage(pass_count,fail_count,skip_count));
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return json;
	}

	
	public static void postSlackMessage(int pass_count,int fail_count,int skip_count)
	{
		String res= createJsonString(pass_count,fail_count,skip_count);
		
		
		RestAssured.baseURI="https://hooks.slack.com";
		
	Response resp=	given().header("Content-type","application/json").body(res).when().
		post("/services/T04F40YC7/BD29XKSA1/tDVSwMM7Wggh4evEk4J63iu6").then().
		assertThat().statusCode(200).extract().response();
	
	
	
	String result=resp.toString();
	System.out.println(result);
	JsonPath path= new JsonPath(result);
	
		
	}


}
