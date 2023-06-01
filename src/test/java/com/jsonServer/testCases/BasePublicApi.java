package com.jsonServer.testCases;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BasePublicApi {

    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 300;
        RestAssured.basePath = "";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
