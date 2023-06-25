package com.jsonServer.testCases.PublicRestApis.read;

import com.jsonServer.testCases.PublicRestApis.BasePublicApi;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StaticReadStudentTest extends BasePublicApi {

    @Test(priority=0)
    public void getStudentsShouldSucceed(){
        given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body();

    }
    @Test(priority=1)
    public void getSingleStudentShouldSucceed(){
        given()
                .log().uri()
                .when()
                .get("/students/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("id",equalTo(2));
    }

}
