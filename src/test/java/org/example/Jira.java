 package org.example;
import io.restassured.RestAssured;import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;
public class Jira {
    public static void main(String[] args) {        // TODO Auto-generated method stub
        RestAssured.baseURI = "https://vijenderchityala.atlassian.net/";
        String createIssueResponse = given().header("Content-Type", "application/json")
                .header("Authorization", "Basic dmlqZW5kZXIuY2hpdHlhbGExMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwV3E5eGhyU0UwLUl3NkNPT2FOUFVUZnJURlZVZFYxdENvVGoxODVPbG80SkpoR0MyaW1mYzQ4WUI3VDlqbGx5ekczeWZxVG9malJjYURLMGVGVG1mNnVLZk9QTGhCa2Q3c1RvOC1hSGZ4SjJiZnhUbC00OFZlYjFzQTY0Rm1iLWtRc3c1ejJIdVdDV094ZDlkT0xRUnVtdEsyOERRLXVRLWFOWTlfbGJHVlAwPTVCOEYyOEFE")
                .body("{\n" + "    \"fields\": {\n" + "       \"project\":\n" + "       {\n" + "          \"key\": \"SCRUM\"\n" + "       },\n" + "       \"summary\": \"Website items are not working- automation Rest Assured\",\n" + "       \"issuetype\": {\n" + "          \"name\": \"Bug\"\n" + "       }\n" + "   }\n" + "}")
                .log().all().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).contentType("application/json").extract().response().asString();
        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);

        given().pathParam("key", issueId)
                .header("X-Atlassian-Token", "no-check").header("Authorization", "Basic dmlqZW5kZXIuY2hpdHlhbGExMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwV3E5eGhyU0UwLUl3NkNPT2FOUFVUZnJURlZVZFYxdENvVGoxODVPbG80SkpoR0MyaW1mYzQ4WUI3VDlqbGx5ekczeWZxVG9malJjYURLMGVGVG1mNnVLZk9QTGhCa2Q3c1RvOC1hSGZ4SjJiZnhUbC00OFZlYjFzQTY0Rm1iLWtRc3c1ejJIdVdDV094ZDlkT0xRUnVtdEsyOERRLXVRLWFOWTlfbGJHVlAwPTVCOEYyOEFE")
                .multiPart("file", new File("C:/Users/VIJENDER/DSC3919.jpeg")).log().all()
                .post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
        //Add attachment		 		 		 		 		 							}
    }
}
