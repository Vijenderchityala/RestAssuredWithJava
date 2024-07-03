package org.example;

import files.Request;
import files.ReusableMethods;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath js=new JsonPath(Request.ComplexJson());

       int count= js.getInt("courses.size()");
        System.out.println(count);

        int totalamt=js.getInt("dashboard.purchaseAmount");
        System.out.println(totalamt);

        String firstcourseTitle=js.get("courses[0].title");
        System.out.println(firstcourseTitle);

        for (int i=0;i<count;i++){
            String coursetitle=js.get("courses["+i+"].title");
            System.out.println(coursetitle);
            System.out.println(js.get("courses["+i+"].price").toString());
        }
int sum=0;
//Compare whether totalpurchase amount matches to all sold copies amount
        for (int i=0;i<count;i++){
          sum=sum+ js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
        }
        System.out.println(sum);
    }


}
