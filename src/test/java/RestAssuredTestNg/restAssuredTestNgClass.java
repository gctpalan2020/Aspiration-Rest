package RestAssuredTestNg;

import java.io.IOException;
import org.testng.annotations.Test;

import RestAssuredApiCalls.RestApiValidation;


public class restAssuredTestNgClass {

	RestApiValidation restObj = new RestApiValidation();
	
	@Test
	void TestNgValidateHeightGreaterThan200() throws IOException{
		restObj.GetCallValidateheightGreaterThan200();
	}
	

}
