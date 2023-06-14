package com.jsonServer.testCases.read;

import com.jsonServer.testCases.BasePublicApi;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicReadStudentTest extends BasePublicApi {
    @Test(priority=0)
    public void getStudentsShouldSucceed(){

        int studentId = given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .when()
                .get("/students/{studentId}",studentId)
                .then()
                .statusCode(200)
                .log().body();

    }
    @Test(priority=1)
    public void getSingleStudentShouldSucceed(){
        int studentId = given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .when()
                .get("/students/{studentId}",studentId)
                .then()
                .statusCode(200)
                .log().body()
                .body("id",equalTo(studentId));
    }

}
