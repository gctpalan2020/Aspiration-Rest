package RestAssuredTestNg;

import java.io.IOException;
import org.testng.annotations.Test;

import RestAssuredApiCalls.RestApiValidation;


public class restAssuredTestNgClass {

	@Test
	void TestNgValidateHeightGreaterThan200() throws IOException{
		
		RestApiValidation restObj = new RestApiValidation();
		restObj.GetCallValidateheightGreaterThan200();
	}
	

}
