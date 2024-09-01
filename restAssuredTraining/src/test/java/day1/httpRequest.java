package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class httpRequest {

	private static final String periority = null;
	int id;
    @Test(priority =1)
    void getUsers() {
    
         when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .log().all();
    }
    
    @Test(priority =2)
    
    void createUser()
    {
    	
    	HashMap data = new HashMap();
    	data.put("name", "paven");
    	data.put("job", "trainer");
    	
    	id=given()
    	.contentType("application/json")
    	.body(data)
    	
    	.when()
    	.post("https://reqres.in/api/users")
    	.jsonPath().getInt("id")
    	
    	;
    	
   /* 	.then()
    	.statusCode(201)
    	.log().all();*/	
    	
    }
    @Test(priority =3)
    void updateUser()
    {

    	HashMap data = new HashMap();
    	data.put("name", "ro2a");
    	data.put("job", "engineer");
    	
    	given()
    	
    	.contentType("application/json")
    	.body(data)
    	
    	.when()
    	.post("https://reqres.in/api/users"+id)
    	
    	
    	.then()
    	.statusCode(201)
    	.log().all();
    }
    @Test(priority =4)
    void deleteUser()
    {
    	
    	when()
    	.delete("https://reqres.in/api/users"+id)
    	.then()
    	.statusCode(204)
    	.log().all();
    }
}
