package day3;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class coockiesDemo {

	//@Test
	void testcoockies()
	{
		given()
		.when()
		.get("https://www.google.com/")
		.then()
		.log().all();
	}
	
	//@Test
	void getthecoockies()
	{
		Response res = given()
		
		.when()
		.get("https://www.google.com/");
	//	String coockiesvalue = res.getCookie("AEC");
	//	System.out.println("coocies value ="+coockiesvalue);
		
		//get all coockies values
		
		Map<String,String> coockies_value=res.getCookies();
		for(String k:coockies_value.keySet())
		{
			String coockie_value = res.getCookie(k);
			System.out.println(k+""+coockie_value);
		}
	}
//	@Test
		void testheaders()
		{
			given()
			.when()
			.get("https://www.google.com/")
			.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding","gzip")
			.log().all();
		}
		
		@Test
		void getheaders()
		{
			Response res = given()
			
			.when()
			.get("https://www.google.com/");
	//get a single header info
			
		//String headervalue	=res.getHeader("Content-Type");
		//System.out.println("the header value ="+headervalue);
		
	//get all headers info
			
			Headers myheaders=  res.getHeaders(); 
			
			for(Header hd:myheaders)
			{
				 System.out.println(hd.getName()+"    "+hd.getValue());
			}
		}
}
