package com.jsonServer.testCases.read;

import com.jsonServer.testCases.BasePublicApi;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StaticReadStudentTest extends BasePublicApi {

    @Test
    public void getStudentsShouldSucceed(){
        given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body();

    }
    @Test
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
