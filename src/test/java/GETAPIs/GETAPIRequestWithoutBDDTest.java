package GETAPIs;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class GETAPIRequestWithoutBDDTest {

    RequestSpecification request;

    //Non BDD Approach

    @BeforeTest
    public void setUp(){
        //Request creation part
        RestAssured.baseURI = "https://gorest.co.in";
        request =  RestAssured.given();
        request.header("Authorization", "1530db812fab1003e4c27a168f011545aa4e07080957263b75cd87e085b781be");
    }

    @Test
    public void getAllUserAPITest(){
        Response response = request.get("public/v2/users");
        //status code
        int statusCode = response.statusCode();
        System.out.println("status code returned by the api: " + statusCode);
        //verification point
        Assert.assertEquals(statusCode, 200, "expected 200 status code form the api, where as the api is throwing "+ statusCode);
        //fetch the body
        response.prettyPrint();
        //fetch headers
        String contentType = response.header("Content-type");
        //fetch all headers
        List<Header> headersList =  response.headers().asList();
        System.out.println("size of headerList is = " + headersList.size());
        headersList.forEach(header -> {
            System.out.println(header.getName()+":"+header.getValue());
        });
    }

    @Test
    public void getSpecificUserTest(){
        //set query params
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("name","Shwet Bhattathiri");
        queryParams.put("gender","male");
        request.queryParams(queryParams);

        Response response = request.get("public/v2/users");
        //status code
        int statusCode = response.statusCode();
        System.out.println("status code returned by the api: " + statusCode);
        //fetch the body
        response.prettyPrint();
    }
}