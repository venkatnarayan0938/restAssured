package GETAPIs;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GETAPIRequestWithBDDTest {

    @Test
    public void getAllUserAPITest(){
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("name","Shwet Bhattathiri");
        queryParams.put("gender","male");

        given()
            .baseUri("https://gorest.co.in")
        .when()
            .header("Authorization", "1530db812fab1003e4c27a168f011545aa4e07080957263b75cd87e085b781be")
            .queryParams(queryParams)
            .get("public/v2/users")
        .then().log().all()
             .assertThat()
             .statusCode(200)
             .and()
             .body("$.size()", equalTo(1));
    }
}
