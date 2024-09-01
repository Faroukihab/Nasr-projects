package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PathAndQueyparameters {

	@Test
	void testQueyAndpathparameters()
	{
		//http://reqres.in/api/users?page=2&id=5
		 
		given()
	//	 .pathParam("path1","api")
		 .pathParam("path2", "users")
		 .queryParam("page",2)
		 .queryParam("id", 3)
		 
		 .when()
		 .get("http://reqres.in/api/{path2}")
		 
		 .then()
		 .statusCode(200)
		 .log().all();
	}
}
