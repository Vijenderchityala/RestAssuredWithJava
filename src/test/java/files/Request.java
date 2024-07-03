package files;

public class Request {

    public static String Addplace() {
        return "{\r\n" +
                "  \"location\": {\r\n" +
                "    \"lat\": -38.383494,\r\n" +
                "    \"lng\": 33.427362\r\n" +
                "  },\r\n" +
                "  \"accuracy\": 50,\r\n" +
                "  \"name\": \"Rahul Shetty Academy\",\r\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" +
                "  \"address\": \"29, side layout, cohen 09\",\r\n" +
                "  \"types\": [\r\n" +
                "    \"shoe park\",\r\n" +
                "    \"shop\"\r\n" +
                "  ],\r\n" +
                "  \"website\": \"http://rahulshettyacademy.com\",\r\n" +
                "  \"language\": \"French-IN\"\r\n" +
                "}\r\n" +
                "";
    }

public static String ComplexJson(){
    return "{\n" +
            "\n" +
            "\"dashboard\": {\n" +
            "\n" +
            "\"purchaseAmount\": 910,\n" +
            "\n" +
            "\"website\": \"rahulshettyacademy.com\"\n" +
            "\n" +
            "},\n" +
            "\n" +
            "\"courses\": [\n" +
            "\n" +
            "{\n" +
            "\n" +
            "\"title\": \"Selenium Python\",\n" +
            "\n" +
            "\"price\": 50,\n" +
            "\n" +
            "\"copies\": 6\n" +
            "\n" +
            "},\n" +
            "\n" +
            "{\n" +
            "\n" +
            "\"title\": \"Cypress\",\n" +
            "\n" +
            "\"price\": 40,\n" +
            "\n" +
            "\"copies\": 4\n" +
            "\n" +
            "},\n" +
            "\n" +
            "{\n" +
            "\n" +
            "\"title\": \"RPA\",\n" +
            "\n" +
            "\"price\": 45,\n" +
            "\n" +
            "\"copies\": 10\n" +
            "\n" +
            "}\n" +
            "\n" +
            "]\n" +
            "\n" +
            "}\n" +
            "\n";
        }

        public static String Addbook(String isbn,String aile){
        String req="{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aile+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";
        return req;
        }

        public static String createIssuejson(){
        return "{\n" +
                "   \"fields\": {\n" +
                "      \"project\":\n" +
                "      {\n" +
                "         \"key\": \"SCRUM\"\n" +
                "       },\n" +
                "      \"summary\": \"Issue created from Restassured java with attachment\",      \n" +
                "      \"issuetype\": {\n" +
                "    \"name\": \"Bug\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        }
    }

