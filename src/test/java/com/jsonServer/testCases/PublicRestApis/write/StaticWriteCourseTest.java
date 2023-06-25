package com.jsonServer.testCases.PublicRestApis.write;

import com.jsonServer.testCases.PublicRestApis.BasePublicApi;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StaticWriteCourseTest extends BasePublicApi {

    @Test
    public void createCourseUsingMapShouldSucceed(){
        String courseName = LoremIpsum.getInstance().getName();
        Map<Object,Object> json = new HashMap<>();
        json.put("name",courseName);

        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(json)
                .when()
                .post("/courses")
                .then()
                .statusCode(201)
                .log().body()
                .body("name", equalTo(courseName))
                .body("id",notNullValue());

    }
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
}
