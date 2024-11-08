package POSTAPIs;

import POJO.CreateUser;
import POJO.Credentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookingAuthWithPOJOTest {

    //POJO - plain Old Java Object
    //pojo is nothing but creating an equivalent java class that resembles Json/XML
    //classic example of encapsulation
    //can not extend any other classes
    //private class, variables as per Json body
    //public getters and setters to access private var & classes

    //serialization --> java object to other format: file, media, json/xml/text/pdf
    //pojo to json - serialization
    //json to pojo - de-serialization

    //FYI: Just creating a pojo class and passing it as request is enough. previous we need to create the code for pjo to json conversion, right now restassured will handle it
    //we have restAssured method that will do the task we need to specify an Object request, and it will be automatically serialised to JSON or XML and sent with the request.
    //need to add a JSON serialization library, such as Jackson, Gson, Johnzon, or Yasson, to your project’s dependencies. for converting an object to JSON

    @Test
    public void postCallWithPOJO(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Credentials cred = new Credentials("admin", "password123");

        String token = given()
                .contentType(ContentType.JSON)
                .body(cred)
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

    public String randomEmail(){
        int randomNumber = new Random().nextInt(10000); // Generates a number between 0 and 9999
        return "random" + randomNumber + "@example.com";
    }
    @Test
    public void addNewUserWithPojo(){
        RestAssured.baseURI = "https://gorest.co.in";
        CreateUser newUser = new CreateUser("test",randomEmail(),"male","active");

        int userId =
                given()
                        .header("Authorization", "Bearer 1530db812fab1003e4c27a168f011545aa4e07080957263b75cd87e085b781be")
                        .contentType(ContentType.JSON)
                        .body(newUser)
                        .when().log().all()
                        .post("public/v2/users/")
                        .then().log().all()
                        .assertThat()
                        .statusCode(201)
                        .and()
                        .body("name", equalTo(newUser.getName()))
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
