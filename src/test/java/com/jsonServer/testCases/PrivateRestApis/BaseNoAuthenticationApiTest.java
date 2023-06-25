package com.jsonServer.testCases.PrivateRestApis;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseNoAuthenticationApiTest {
    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "https://apingweb.com";
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
