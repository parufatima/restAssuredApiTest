package com.jsonServer.testCases.PublicRestApis.read;

import com.jsonServer.testCases.PublicRestApis.BasePublicApi;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicReadCourseTest extends BasePublicApi {

    @Test
    public void getCoursesShouldSucceed(){
        int courserId = given()
                .log().uri()
                .when()
                .get("/courses")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .when()
                .get("/courses/{courserId}",courserId)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(courserId));
    }

}
