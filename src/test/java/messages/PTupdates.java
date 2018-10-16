package messages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class PTupdates {
	
	public List<HashMap<String, Object>>  getBugUpdates()
	{
		
		RestAssured.baseURI="https://www.pivotaltracker.com";
		
		Response resp= given().contentType("JSON").header("X-TrackerToken","d7fe7723f4bbbf4d14e689344f10cb01").header("Content-Type","application/json").
				when().get("/services/v5/projects/2200511/stories").then().extract().response();
		
		String res=resp.asString();
	//	JsonPath js= new JsonPath(res);
		
	//	String myres=js.get("$[?(@.story_type=='bug')]");
		List<HashMap<String, Object>> resultMap = new ArrayList<HashMap<String,Object>>();
		
		ObjectMapper mapperObj = new ObjectMapper();
		
        
 //       System.out.println("Input Json String: "+res);
        try {
            resultMap = mapperObj.readValue(res, 
                            new TypeReference<List<HashMap<String,Object>>>(){});
            System.out.println(resultMap);
        }
        catch(JsonGenerationException e)
        {
        	e.printStackTrace();
        }
        catch(JsonMappingException e)
        {
        	e.printStackTrace();
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
        
        return resultMap;
				
	}
	
	public String[]  getTCName()
	{
		
		List<HashMap<String, Object>> myList=getBugUpdates();
		
		HashMap<String,Object> innermap= new HashMap<String,Object>();
		String[] tcName= new String[myList.size()];
		for(int i=0;i<myList.size();i++)
		{
			innermap=myList.get(i);
			
			if((!innermap.get("current_state").toString().equals("accepted")) && innermap.get("story_type").toString().equals("bug"))
			
		 tcName[i]= innermap.get("name").toString();
		 
		
		else
		{
			tcName[i]="";
		}
		}
		
		return tcName;
		
		
	}
	
	public static void main (String[] args)
	{
		PTupdates pt= new PTupdates();
		for(String s:pt.getTCName())
		System.out.println(s);
	}

}
