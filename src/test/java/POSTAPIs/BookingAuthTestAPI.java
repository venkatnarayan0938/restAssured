package POSTAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookingAuthTestAPI {

    @Test
    public void postCallWithJSONString(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String token = given()
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                 "username" : "admin",
                                  "password" : "password123"
                                }""")
                        .when()
                        .post("/auth")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .and()
                        .extract()
                        .path("token");

        System.out.println("token: " + token );
        Assert.assertNotNull(token,"token is null");
    }

    @Test
    public void postCallWithJSONFile(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String token = given()
                .contentType(ContentType.JSON)
                .body(new File("src/res/requestbodies/booker.json"))
                .when()
                .post("/auth")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("token");

        System.out.println("token: " + token );
        Assert.assertNotNull(token,"token is null");
    }

    @Test
    public void addNewUser(){
        RestAssured.baseURI = "https://gorest.co.in";

        int userId =
        given()
                .header("Authorization", "Bearer 1530db812fab1003e4c27a168f011545aa4e07080957263b75cd87e085b781be")
                .contentType(ContentType.JSON)
                .body(new File("src/res/requestbodies/addUser.json"))
        .when().log().all()
                .post("public/v2/users/")
        .then().log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body("name", equalTo("__NAME__"))
                .extract()
                .path("id");

        given()
                .header("Authorization", "Bearer 1530db812fab1003e4c27a168f011545aa4e07080957263b75cd87e085b781be")
                .when()
                .get("public/v2/users/" + userId)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", equalTo(userId));
    }
}
