package com.jsonServer.testCases.write;

import com.jsonServer.testCases.BasePublicApi;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StaticWriteStudentTest extends BasePublicApi {
    @Test(priority=0)
    public void createStudentUsingMapShouldSucceed(){

        String studentName = LoremIpsum.getInstance().getName();
        Map<String,Object> json = new HashMap<>();
        json.put("name", studentName);
        json.put("courseId", 2);

        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(json)
                .log().body()
                .when()
                .post("/students")
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                .body("name", equalTo(studentName))
                .body("courseId", equalTo(2));
    }
    @Test(priority=1)
    public void createStudentUsingJsonShouldSucceed(){

        String studentName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", studentName);
        jsonObject.put("courseId", 2);

        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(jsonObject)
                .log().body()
                .when()
                .post("/students")
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                .body("name", equalTo(studentName))
                .body("courseId", equalTo(2));
    }
    @Test(priority=2)
    public void updateStudentUsingJsonShouldSucceed(){

        String studentName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", studentName);
        jsonObject.put("courseId", 2);

        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(jsonObject)
                .log().body()
                .when()
                .put("/students/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", notNullValue())
                .body("name", equalTo(studentName))
                .body("courseId", equalTo(2));
    }
    @Test(priority=3)
    public void replaceStudentUsingJsonShouldSucceed(){

        String studentName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", studentName);


        given()
                .header("Content-Type","application/json")
                .log().uri()
                .body(jsonObject)
                .log().body()
                .when()
                .patch("/students/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", notNullValue())
                .body("name", equalTo(studentName))
                .body("courseId", equalTo(2));
    }
    @Test(priority=4)
    public void deleteStudentUsingJsonShouldSucceed(){
        int studentId= given()
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
                .delete("/students/{studentId}",studentId)
                .then()
                .statusCode(200)
                .log().body();


    }
}
