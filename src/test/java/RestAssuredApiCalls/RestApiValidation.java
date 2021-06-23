package RestAssuredApiCalls;

import static io.restassured.RestAssured.get;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import helperPack.helperClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiValidation {
	
	helperClass helperClassObj = new helperClass();
	SoftAssert softAssert = new SoftAssert();

	public void GetCallValidateheightGreaterThan200() throws IOException {
		
		
		List<String> listWithGreaterThan200 = Arrays.asList(
				helperClassObj.getArrayValueFromPropFile("personNamesWhoseHeightGreaterThan200"));
		
		String nextUrl = helperClassObj.getStringValueFromPropFile("baseUrl");
		
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
				String name =jpath.getString("results["+i+"].name");
				
				if (!("unknown".equalsIgnoreCase(height))) {
					if(Integer.parseInt(height)>200) {
						
						listOfMembersHeightGreaterThan200++;
						softAssert.assertTrue(listWithGreaterThan200.contains(name), "API returned name - "+name +" - whose height is greater than 200 But its not in the provided list ");
						
//						Assert.assertTrue(listWithGreaterThan200.contains(name), "API returned name - "+name +" - whose height is greater than 200 But its not in the provided list ");
					}
					
				}
			
			}
			
		}
		
//		Assert.assertEquals(totalSizeofResults, 80, "Total size of elements returned by API is not equal to 81 - but it is "+totalSizeofResults);
//		Assert.assertEquals(listOfMembersHeightGreaterThan200, 10, "Number of people with height greater than 200 is not 10 - but it is "+listOfMembersHeightGreaterThan200);
		
		softAssert.assertEquals(totalSizeofResults, 81, "Total size of elements returned by API is not equal to 81 - but it is "+totalSizeofResults);
		softAssert.assertEquals(listOfMembersHeightGreaterThan200, 10, "Number of people with height greater than 200 is not 10 - but it is "+listOfMembersHeightGreaterThan200);
		softAssert.assertAll();
	
	}
}
