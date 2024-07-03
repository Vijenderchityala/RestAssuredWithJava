package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceAPITest {
    public static void main(String[] args) {
//Login
        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

        LoginRequest loginRequest= new LoginRequest();
        loginRequest.setUserEmail("vijender@qa.com");
        loginRequest.setUserPassword("Connected@3");
        //LoginResponse loginResponse=new LoginResponse();
        RequestSpecification reqlogin= given().log().all().spec(req).body(loginRequest);
        LoginResponse loginres=reqlogin.when().post("api/ecom/auth/login").then().log().all()
                            .extract().response().as(LoginResponse.class);
        String token=loginres.getToken();
        System.out.println(token);
        String userid=loginres.getUserId();
        System.out.println(userid);
//CreateProduct
        RequestSpecification req2= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization",token).build();

        RequestSpecification createproductreq=given().log().all().spec(req2).param("productName","Laptop")
                .param("productAddedBy",userid)
                .param("productCategory","Fashion")
                .param("productSubCategory","Shirts")
                .param("productPrice","11500")
                .param("productDescription","HP")
                .param("productFor","All")
                .multiPart("productImage",new File("C://Users//VIJENDER//Postman//files//pexels-mayday-1545743.jpg"));
        String responseobj=createproductreq.when().post("api/ecom/product/add-product").then().log().all()
                .extract().response().asString();
        JsonPath js = new JsonPath(responseobj);
        String productId=js.get("productId");
        //OrderProduct

        RequestSpecification createOrderbasereq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).addHeader("authorization",token).build();

        OrderDetail orderDetail= new OrderDetail();
        orderDetail.setCountry("India");
        orderDetail.setProductOrderedId(productId);
        List<OrderDetail> orderDetailList= new ArrayList<OrderDetail>();
        orderDetailList.add(orderDetail);


        Orders orders = new Orders();
        orders.setOrders(orderDetailList);
        RequestSpecification createOrderReq=given().log().all().spec(createOrderbasereq).body(orders);
        String responseAddOrder=createOrderReq.when().post("api/ecom/order/create-order").then().log().all()
                .extract().response().asString();
        System.out.println(responseAddOrder);


        //DeleteProduct
        RequestSpecification deleteproductbasereq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).addHeader("authorization",token).build();
        RequestSpecification deleteproductreq=given().log().all().spec(deleteproductbasereq).pathParam("productId",productId);
       String delresp= deleteproductreq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all()
                .extract().response().asString();
       JsonPath js3= new JsonPath(delresp);
        Assert.assertEquals("Product Deleted Successfully",js3.get("message"));

    }
}
