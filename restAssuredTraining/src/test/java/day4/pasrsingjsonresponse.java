package day4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import jdk.internal.net.http.common.Log;

public class pasrsingjsonresponse {

	@Test
	void testjsonresponse() {
		//ِِApproch1
		
     /*   given()
            .contentType("application/json")  // Correct the content type
        .when()
            .get("http://localhost:3000/Students_data")
        .then()
            .statusCode(200)
            .header("Content-Type", equalTo("application/json"))
            .body("[0].name", equalTo("john"))
            .log().all();*/
		
		//Approch2
		
		Response res=given()
		 .contentType(ContentType.JSON)  
		 .when()
		 .get("http://localhost:3000/Students_data");
		 
	/*	Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json");
        String name =res.jsonPath().get("[0].name").toString();	
        Assert.assertEquals(name, "john");*/
         
		 
		//using jsonobject class
		JSONObject jo = new JSONObject(res.asString());
		
		
	}
}
