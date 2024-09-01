package day5;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;

public class parsingXMLResponse {

	
	//Approch1
	
	/*	given()
		.when()
		.get("https://petstore.swagger.io/#/pet/getPetById")
		.then()
		.statusCode(200)
		.header("Content-Type", "text/html")
		  .body("html.head.title", equalTo("Swagger UI"));*/

	

	
	
	//Apporch2
    @Test
    void testXMLResponseBody() {
        Response res = given()
                           .when()
                           .get("https://petstore.swagger.io/#/pet/getPetById");
        XmlPath xmlobj = new XmlPath(XmlPath.CompatibilityMode.HTML, res.asString())
                .using(((Object) new XmlPathConfig().with())
                .disableFeature("http://apache.org/xml/features/disallow-doctype-decl"));
  
        String title = xmlobj.getString("html.head.title");
    assert title.equals("Swagger UI");

    // Validate the presence of <meta> tag with charset attribute
    String metaCharset = xmlobj.getString("html.head.meta.@charset");
    assert metaCharset.equals("UTF-8");

    // Validate the presence of <link> tag with specific attributes
    String linkRel = xmlobj.getString("html.head.link.find { it.@rel == 'stylesheet' && it.@href == './swagger-ui.css' }.@rel");
    assert linkRel.equals("stylesheet");

    // Validate the presence of <script> tag with src attribute containing 'gtm.js'
    String scriptSrc = xmlobj.getString("html.head.script.find { it.@src.contains('gtm.js') }.@src");
    assert scriptSrc.contains("gtm.js");

    // Validate the presence of <div> with id 'swagger-ui'
    String divId = xmlobj.getString("html.body.div.@id");
    assert divId.equals("swagger-ui");

    // Validate the presence of <link> tag for favicon
    String faviconLink = xmlobj.getString("html.head.link.find { it.@rel == 'icon' && it.@sizes == '32x32' && it.@href == './favicon-32x32.png' }.@href");
    assert faviconLink.equals("./favicon-32x32.png");
    }
}
