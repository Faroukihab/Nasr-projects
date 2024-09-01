package day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;



/*
 * Their are four ways to generate post req. body :
 * 1.Hashmap method.
 * 2.org.json method
 * 3. pojo method (plain old java object)
 * 4. external json file
 */

//1)Post body using hashmap Method 

public class theWaystoCreatPostRequestBody {

  //  @Test (priority =1)
    void postUsingHashMap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "ziad");
        data.put("job", "DC");
        String[] coursesArr = {"c", "c++"};
        data.put("courses", coursesArr);
        
        given()
            .contentType("application/json")
            .body(data) 
        .when()
            .post("http://localhost:3000/Students_data")
        .then()
            .statusCode(201)
            .body("name", equalTo("ziad"))
            .body("job", equalTo("DC"))
            .body("courses[0]", equalTo("c"))
            .body("courses[1]", equalTo("c++"))
            .log().all();
    }
   
    //2)Post body using org.json
 //  @Test(priority = 1)
    void postUsingJsonLibrary() {
        JSONObject data = new JSONObject();
        data.put("name", "ziad");
        data.put("job", "DC");
        String[] coursesArr = {"c", "c++"};
        data.put("courses", coursesArr);
        
        System.out.println("Request Body: " + data.toString());

        given()
            .contentType("application/json")
            .body(data.toString()) 
        .when()
            .post("http://localhost:3000/Students_data")
        .then()
            .statusCode(201)
            .log().all()
            .body("name", equalTo("ziad"))
            .body("job", equalTo("DC"))
            .body("courses[0]", equalTo("c"))
            .body("courses[1]", equalTo("c++"));
    }

    //3)Post request body using pojo
    
 //  @Test(priority = 1)
    void postUsingPojoClass() {
        Pojo_postrequest data = new Pojo_postrequest();
        data.setName("ziad");
        data.setJob("DC");
        String[] coursesArr = {"c", "c++"};
        data.setCourses(coursesArr);

        // Serialize POJO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "";

        try {
            jsonBody = objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        given()
            .contentType("application/json")
            .body(jsonBody) 
        .when()
            .post("http://localhost:3000/Students_data")
        .then()
            .statusCode(201)
            .log().all()
            .body("name", equalTo("ziad"))
            .body("job", equalTo("DC"))
            .body("courses[0]", equalTo("c"))
            .body("courses[1]", equalTo("c++"))
            .log().all();
    }
    
    //4) post request body using external JSON file
   
    @Test(priority = 1)
    void postUsingexternaljsonfile() throws FileNotFoundException {
        // Specify the JSON file path
        File f = new File("./body.json");

        // Ensure the file exists
        if (!f.exists()) {
            throw new FileNotFoundException("File not found: " + f.getAbsolutePath());
        }

        // Read the JSON file
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        // Convert the JSON object to a string
        String jsonBody = data.toString();

        given()
            .contentType("application/json")
            .body(jsonBody)  // Use the JSON string here
        .when()
            .post("http://localhost:3000/Students_data")
        .then()
            .statusCode(201)
            .log().all()
            .body("name", equalTo("ziad"))
            .body("job", equalTo("DC"))
            .body("courses[0]", equalTo("c"))
            .body("courses[1]", equalTo("c++"))
            .log().all();
    }
      
    
    @Test(priority = 2)
    void delettherequest()
    {
    	when()
    	.delete("http://localhost:3000/Students_data/4");
    	
    }
}
