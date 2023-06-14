package com.jsonServer.testCases.read;

import com.jsonServer.testCases.BasePublicApi;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class StaticReadCoursesTest extends BasePublicApi {

    @Test(timeOut = 2000)
    public void getCoursesShouldSucceed(){
        given()
                .log()
                .uri()
                .when()
                .get("/courses")
                .then()
                .statusCode(200)
                .log().body();

    }
}
