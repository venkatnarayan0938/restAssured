package JSONPathValidatorTest;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class JSONPathTest {

    @Test
    public void getCircuitDetails(){
        RestAssured.baseURI = "https://ergast.com";

        Response response = given()
                            .when()
                            .get("/api/f1/2016/circuits.json");

        String jsonResponse = response.asString();
        System.out.println(jsonResponse);

        List<String> circuitIds = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits[*].circuitId");
        System.out.println(circuitIds);
        int circuitCount = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
        System.out.println(circuitCount);
        List<String> country = JsonPath.read(jsonResponse,"$.MRData.CircuitTable.Circuits[?(@.Location.country=='Australia' && @.Location.locality=='Melbourne')].circuitName");
        System.out.println("circuit name in Australia: " + country);
        List<Map<String,Object>> circuitDetails = JsonPath.read(jsonResponse,"$.MRData.CircuitTable.Circuits[*].[\"circuitId\",\"circuitName\", \"url\"]");
        for(Map<String,Object> detail : circuitDetails){
             String id = (String) detail.get("circuitId");
             String name = (String) detail.get("circuitName");
             String url = (String) detail.get("url");
             System.out.println("id: "+ id + "name: "+ name + "url: " + url);
        }
    }

}
