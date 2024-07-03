package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class SerialzeTest {
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

        RestAssured.baseURI="https://rahulshettyacademy.com";

        Response res=given().queryParam("key","qaclick123")
                .body(a).post("/maps/api/place/add/json").then()
                .assertThat().statusCode(200).extract().response();
        String responseSting = res.asString();
        System.out.println(responseSting);
    }
}
