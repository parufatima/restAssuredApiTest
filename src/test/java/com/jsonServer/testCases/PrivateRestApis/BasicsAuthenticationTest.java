package com.jsonServer.testCases.PrivateRestApis;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicsAuthenticationTest extends BasePrivateApiTest{

    @Test(priority=0)
    public void userListShouldSucceed(){

        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin","12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().body()
                .body("success",equalTo(true))
                .body("message",equalTo("Success"));

    }
    @Test(priority=1)
    public void singleUserDetailShouldSucceed(){

       int userId= given()
                .contentType(ContentType.JSON)
                .auth().basic("admin","12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
            //    .log().body()
                .body("success",equalTo(true))
                .body("message",equalTo("Success"))
                .extract().jsonPath().getInt("data[0].user_id");


        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin","12345")
                .log().uri()
                .when()
                .get("/user/{userId}",userId)
                .then()
                .statusCode(200)
                .log().body()
                .body("success",equalTo(true))
                .body("message",equalTo("Success"));

    }
    @Test(priority=2)
    public void createUserShouldSucceed(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name",LoremIpsum.getInstance().getName());
        jsonObject.put("age",35);
        jsonObject.put("image","https://example.com/batman.png");

        given()
                .auth().basic("admin","12345")
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/user/create")
                .then()
                .log().body()
                .statusCode(200)
                .body("message",equalTo("Success"))
                .body("status",equalTo(200))
                .body("success",equalTo(true));
    }
    @Test(priority=3)
    public void updateUserShouldSucceed(){
        int userId= given()
                .contentType(ContentType.JSON)
                .auth().basic("admin","12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                //    .log().body()
                .body("success",equalTo(true))
                .body("message",equalTo("Success"))
                .extract().jsonPath().getInt("data[0].user_id");


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name",LoremIpsum.getInstance().getName());
        jsonObject.put("age",35);
        jsonObject.put("image","https://example.com/batman.png");

        given()
                .auth().basic("admin","12345")
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/user/edit/{userId}",userId)
                .then()
                .log().body()
                .statusCode(200)
                .body("message",equalTo("Success"))
                .body("status",equalTo(200))
                .body("success",equalTo(true));
    }

    @Test(priority=4)
    public void deleteUserShouldSucceed(){
        int userId= given()
                .contentType(ContentType.JSON)
                .auth().basic("admin","12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                //    .log().body()
                .body("success",equalTo(true))
                .body("message",equalTo("Success"))
                .extract().jsonPath().getInt("data[0].user_id");


        given()
                .auth().basic("admin","12345")
                .contentType(ContentType.JSON)
                .log().uri()
                .log().body()
                .when()
                .delete("/user/delete/{userId}",userId)
                .then()
                .log().body()
                .statusCode(200);
    }
}
