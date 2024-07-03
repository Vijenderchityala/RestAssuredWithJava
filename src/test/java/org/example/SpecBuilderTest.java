package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    public static void main(String[] args) {
        AddPlace a= new AddPlace();
        a.setAccuracy(50);
        a.setAddress("29, side layout, cohen 09");
        a.setLanguage("French-IN");
        a.setName("Vijender Reddy");
        a.setPhone_number("(+91) 983 893 3937");
        a.setWebsite("http://rahulshettyacademy.com");
        List<String> list= new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        a.setTypes(list);
        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        a.setLocation(l);

        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
        //RestAssured.baseURI="https://rahulshettyacademy.com";
        ResponseSpecification resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON). build();

        RequestSpecification request=given().spec(req).body(a);

        Response response=request.when().post("/maps/api/place/add/json")
                .then().spec(resspec).extract().response();

        Response resp=request.post("/maps/api/place/add/json").then()
                .spec(resspec).extract().response();
        String responseSting = resp.asString();
        System.out.println(responseSting);
    }
}
