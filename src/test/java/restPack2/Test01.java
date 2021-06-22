package restPack2;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

public class Test01 {

	@Test
	void test_01() {
		
		
//		Response response = get("https://swapi.dev/api/people");
////		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getBody());
//		System.out.println(response.asString());
//		System.out.println(response.getStatusLine());
//		System.out.println(response.getContentType());
//		System.out.println(response.getTime());
		
		
		
		List<String> listWithGreaterThan200 = Arrays.asList("Darth Vader", 
		"Chewbacca","Roos Tarpals","Rugor Nass","Yarael Poof","Lama Su","Tuan Wu",
		"Grievous","Tarfful","Tion Medon");
		
		String nextUrl = "https://swapi.dev/api/people";
		
		int totalSizeofResults = 0;
		int listOfMembersHeightGreaterThan200 = 0;
		
		while(nextUrl!=null) {
			
			Response getResponse = get(nextUrl);
			int statusCode = getResponse.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			
			JsonPath jpath = getResponse.jsonPath();
			
			int currentSizeofResults = jpath.getInt("results.size()");
			totalSizeofResults += currentSizeofResults;
			nextUrl = jpath.getString("next");
			
			for(int i=0;i<currentSizeofResults;i++) 
			{
			
				String height =jpath.getString("results["+i+"].height");
//				System.out.println(height);
				String name =jpath.getString("results["+i+"].name");
//				System.out.println(name);
				
				if (!("unknown".equalsIgnoreCase(height))) {
					if(Integer.parseInt(height)>200) {
						
						listOfMembersHeightGreaterThan200++;
						Assert.assertTrue(listWithGreaterThan200.contains(name));
					}
					
				}
			
			}
			
		}
		
		Assert.assertEquals(totalSizeofResults, 82);
		
		Assert.assertEquals(listOfMembersHeightGreaterThan200, 10);
		
	
	}
	
//	@Test
//	void test_02() {
//		
//		given()
//		.get("https://swapi.dev/api/people")
//		.then().statusCode(200)
//		.body("results.height", equalTo(7));
//	
//	}
}
