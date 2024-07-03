package org.example;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;


public class GetCouseusingPojo {



    public static void main(String[] args) throws InterruptedException {

// TODO Auto-generated method stub
        String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
        String response =
                given()
                        .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .formParams("grant_type", "client_credentials")
                        .formParams("scope", "trust")
                        .when().log().all()

                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);

        String accessToken = jsonPath.getString("access_token");

        System.out.println(accessToken);

        GetCourse r2=    given()

                .queryParams("access_token", accessToken)
                .when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                        .as(GetCourse.class);

        System.out.println(r2.getInstructor());
        System.out.println(r2.getLinkedIn());
        //Get the price of SoapUI webservices course
        List<Api> apiCourses= r2.getCourses().getApi();
        for(int i=0;i<apiCourses.size();i++){
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase(("SoapUI Webservices testing"))){
                System.out.println(apiCourses.get(i).getPrice());
            }
        }
        //Get courseNames of Web Automation
        ArrayList<String> a= new ArrayList<String>();
        List<WebAutomation> webAutomationcourses =r2.getCourses().getWebAutomation();
        for(int j=0;j<webAutomationcourses.size();j++){
            a.add(webAutomationcourses.get(j).getCourseTitle());
        }
        List<String> expectedCourses= Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expectedCourses));



    }



}

