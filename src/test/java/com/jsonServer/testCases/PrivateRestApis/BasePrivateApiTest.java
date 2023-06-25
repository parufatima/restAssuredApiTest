package com.jsonServer.testCases.PrivateRestApis;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BasePrivateApiTest {

    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "https://apingweb.com";
       // RestAssured.port = 3000;
        RestAssured.basePath = "/api/auth";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
