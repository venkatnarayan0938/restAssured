package GETAPIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GETAPIRequestWithBDDTest {

    @Test
    public void getAllUserAPITest(){
        RestAssured.baseURI = "https://gorest.co.in";
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("name","Shwet Bhattathiri");
        queryParams.put("gender","male");

        given()
             .header("Authorization", "1530db812fab1003e4c27a168f011545aa4e07080957263b75cd87e085b781be")
             .queryParams(queryParams)
        .when()
             .get("public/v2/users")
        .then().log().all()
             .assertThat()
             .statusCode(200)
             .and()
             .body("$.size()", equalTo(1));
    }

    @Test
    public void getProductDetails(){
        RestAssured.baseURI = "https://fakestoreapi.com";
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("limit","5");

        Response response = given()
                                .queryParams(queryParams)
                            .when()
                                .get("/products");

        JsonPath jsonResponse = response.jsonPath();

        (jsonResponse.getList("id")).forEach(id ->{
            System.out.println("id: " + id);
        });
        (jsonResponse.getList("title")).forEach(title ->{
            System.out.println("title: " + title );
        });
        (jsonResponse.getList("rating.count")).forEach(count ->{
            System.out.println("count: " + count);
        });
    }

    @Test
    public void getProductDetailsThroughExtract(){
        RestAssured.baseURI = "https://fakestoreapi.com";
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("limit","5");

        Response response = given()
                .queryParams(queryParams)
                .when()
                .get("/products")
                .then()
                .extract()
                .response();
        System.out.println("id: " + response.path("[0].id"));
    }

    @DataProvider
    public Object[][] getCircuitData(){
        return new Object[][]{
                {"2016", 21},
                {"2017", 20},
                {"2023", 22}
        };
    }

    @Test(dataProvider = "getCircuitData")
    public void getProductDetailsWithPathParam(String seasonYear, int totalCircuits){
        RestAssured.baseURI = "https://ergast.com";

        given()
            .pathParams("year",seasonYear)
        .when()
            .get("/api/f1/{year}/circuits.json")
        .then()
            .statusCode(200)
            .and()
            .body("MRData.CircuitTable.season", equalTo(seasonYear))
            .and()
            .body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));
    }


}
