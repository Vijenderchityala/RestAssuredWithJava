package org.example;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.ReusableMethods;
import files.Request;

public class DynamicJson {

    @Test(dataProvider = "Booksdata")
    public void addBook(String isbn,String aile)
    {
        RestAssured.baseURI="http://216.10.245.166";

        String resp=given().

                header("Content-Type","application/json").

                body(Request.Addbook(isbn,aile)).
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response().asString();

        JsonPath js= ReusableMethods.rawtoJson(resp);

        String id=js.get("ID");

        System.out.println(id);



        //deleteBOok

    }

    @DataProvider(name="Booksdata")
    public Object [][] getData(){
        return new Object[][] {{"abc","12"},{"def","45"},{"yhu","798"}};

     }















}

