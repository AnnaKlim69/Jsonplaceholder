package framework;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import io.qameta.allure.Attachment;
import io.restassured.http.ContentType;
import org.testng.reporters.Files;

import static io.restassured.RestAssured.given;

public class BaseAdapter {
    PropertyReader propertyReader = new PropertyReader("config.properties");
    protected Gson gson = new Gson();
    protected JsonParser jsonParser = new JsonParser();
    public String post(String body, String uri, int status) {
        return given().
                header("Content-type", "appLocation/json").
                body(body).
                when().
                post(PropertyReader.getIntProperty("URL") + uri).
                then().
                log().all().
                statusCode(status).
                and().
                contentType(ContentType.JSON).
                extract().body().asString();
    }
    public String get(String uri, int status) {
        return given().
                header("Content-type", "appLocation/json").
                get(PropertyReader.getIntProperty("URL") + uri).
                then().
                log().all().
                statusCode(status).
                and().
                contentType(ContentType.JSON).
                extract().body().asString();
    }
    public String get(String uri, int id, int status) {
        return given().
                header("Content-type", "appLocation/json").
                get(PropertyReader.getIntProperty("URL") + uri + id).
                then().
                log().all().
                statusCode(status).
                and().
                contentType(ContentType.JSON).
                extract().body().asString();
    }
    public String get(String endUri, int id, int userID, int status) {
        return given().
                header("Content-type", "appLocation/json").
                get(PropertyReader.getIntProperty("URL") + endUri + id + userID).
                then().
                log().all().
                statusCode(status).
                and().
                contentType(ContentType.JSON).
                extract().body().asString();
    }
}