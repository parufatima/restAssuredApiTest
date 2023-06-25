package com.jsonServer.testCases.PrivateRestApis;

import com.google.gson.JsonObject;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BearerTokenAuthenticationTest extends BaseBearerTokenAuthenticationTest {

    @Test(priority = 0)
    public void getArticlesShouldSucceed(){

        given()
                .spec(getRequestSpecification())
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(priority=1)
    public void getSingleArticleShouldSucceed(){

      int articleId=  given()
                .spec(getRequestSpecification())
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("result[0].id");

        given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .get("/article/{articleId}",articleId)
                .then()
                .statusCode(200)
                .log().body();

    }
    @Test(priority=2)
    public void createArticleShouldSucceed(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(1));
        jsonObject.put("body",LoremIpsum.getInstance().getParagraphs(1,1));
        jsonObject.put("picture","https://example.com/lorem.png");


        given()
                .spec(getRequestSpecification())
                .log().uri()
                .body(jsonObject)
                .when()
                .post("/article/create")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message",equalTo("Article has been created"))
                .log().body();

    }
    @Test(priority=3)
    public void updateArticleShouldSucceed(){
        int articleId=  given()
                .spec(getRequestSpecification())
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("result[0].id");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(1));
        jsonObject.put("body",LoremIpsum.getInstance().getParagraphs(1,1));
        jsonObject.put("picture","https://example.com/lorem.png");


        given()
                .spec(getRequestSpecification())
                .log().uri()
                .body(jsonObject)
                .when()
                .put("/article/edit/{articleId}",articleId)
                .then()
                .statusCode(400)
                .log().body();

    }

    @Test(priority=4)
    public void deleteArticleShouldSucceed(){
        int articleId=  given()
                .spec(getRequestSpecification())
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("result[0].id");

        given()
                .spec(getRequestSpecification())
                .log().uri()
                .when()
                .delete("/article/delete/{articleId}",articleId)
                .then()
                .statusCode(400)
                .log().body();

    }


}