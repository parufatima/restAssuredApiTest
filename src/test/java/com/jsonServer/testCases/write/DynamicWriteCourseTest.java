package com.jsonServer.testCases.write;

import com.jsonServer.testCases.BasePublicApi;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DynamicWriteCourseTest extends BasePublicApi {
    @Test
    public void createCourseUsingJsonShouldSucceed(){
        String courseName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",courseName);

        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(jsonObject)
                .when()
                .post("/courses")
                .then()
                .statusCode(201)
                .log().body()
                .body("name",equalTo(courseName))
                .body("id", notNullValue());
    }
    @Test
    public void replaceCourseUsingJsonShouldSucceed(){

       int courseId= given()
                .log().uri()
                .when()
                .get("/courses")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String courseName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",courseName);

        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(jsonObject)
                .when()
                .put("/courses/{courseId}",courseId)
                .then()
                .statusCode(200)
                .log().body()
                .body("name",equalTo(courseName))
                .body("id",equalTo(courseId));

    }
    @Test
    public void updateCourseUsingJsonShouldSucceed(){

        int courseId= given()
                .log().uri()
                .when()
                .get("/courses")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String courseName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",courseName);

        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(jsonObject)
                .when()
                .patch("/courses/{courseId}",courseId)
                .then()
                .statusCode(200)
                .log().body()
                .body("name",equalTo(courseName))
                .body("id",equalTo(courseId));

    }
    @Test
    public void deleteCourseUsingJsonShouldSucceed(){

        int courseId= given()
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
                .delete("/courses/{courseId}",courseId)
                .then()
                .statusCode(200)
                .log().body();


    }



}



