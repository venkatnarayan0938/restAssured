package fake.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetProduct {
    @Test
    public void getProductDetailsWithPOJO() throws JsonProcessingException {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given()
                .when()
                .get("/products");

        //json to pojo mapping: de-serialization
        ObjectMapper mapper = new ObjectMapper();
        ProductResponse product[] = mapper.readValue(response.getBody().asString(), ProductResponse[].class);

        for(ProductResponse p: product){
            System.out.println("-------------------");
            System.out.println("id: "+ p.getId());
            System.out.println("title: "+ p.getTitle());
            System.out.println("price: "+ p.getPrice());
            System.out.println("description: "+ p.getDescription());
            System.out.println("category: "+ p.getCategory());
            System.out.println("image: "+ p.getImage());
            System.out.println("count: "+ p.getRating().getCount());
            System.out.println("rate: "+ p.getRating().getRate());
            System.out.println("-------------------");
        }
    }

    @Test
    public void getProductDetailsWithLombok() throws JsonProcessingException {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given()
                .when()
                .get("/products");

        //json to pojo mapping: de-serialization
        ObjectMapper mapper = new ObjectMapper();
        ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);

        for(ProductLombok p: product){
            System.out.println("-------------------");
            System.out.println("id: "+ p.getId());
            System.out.println("title: "+ p.getTitle());
            System.out.println("price: "+ p.getPrice());
            System.out.println("description: "+ p.getDescription());
            System.out.println("category: "+ p.getCategory());
            System.out.println("image: "+ p.getImage());
            System.out.println("count: "+ p.getRating().getCount());
            System.out.println("rate: "+ p.getRating().getRate());
            System.out.println("-------------------");
        }
    }

    @Test
    public void getProductDetailsWithLombokWithBuilder() throws JsonProcessingException {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response = given()
                .when()
                .get("/products");

    }
}
