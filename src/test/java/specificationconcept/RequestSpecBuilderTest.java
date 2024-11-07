package specificationconcept;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestSpecBuilderTest {

    public static RequestSpecification user_req_spec(){
        RequestSpecification requestSpec = new RequestSpecBuilder()
                                                    .setBaseUri("https://gorest.co.in")
                                                    .addHeader("Authorization", "Bearer 1530db812fab1003e4c27a168f011545aa4e07080957263b75cd87e085b781be")
                                                    .setContentType(ContentType.JSON)
                                                    .build();

        return requestSpec;
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
                 .statusCode(200);
    }
}
