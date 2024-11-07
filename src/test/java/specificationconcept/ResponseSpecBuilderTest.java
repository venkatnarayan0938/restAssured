package specificationconcept;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static specificationconcept.RequestSpecBuilderTest.user_req_spec;

public class ResponseSpecBuilderTest {

    public static ResponseSpecification verify_api_status_code(){
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                                                .expectStatusCode(200)
                                                .expectContentType(ContentType.JSON)
                                                .build();

        return responseSpec;
    }

    @Test
    public void getUserWithRequestSpec(){
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("name","Shwet Bhattathiri");
        queryParams.put("gender","male");

        given()
                .spec(user_req_spec())
                .queryParams(queryParams)
                .get("public/v2/users")
                .then()
                .assertThat()
                .spec(verify_api_status_code());
    }
}
