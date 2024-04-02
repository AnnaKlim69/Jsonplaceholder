package jsonplaceholder.adapters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import framework.BaseAdapter;
import io.qameta.allure.Step;
import jsonplaceholder.modalse.PostModal;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.google.gson.JsonParser.parseString;

public class PostsAdapter extends BaseAdapter {
    SoftAssert softAssert = new SoftAssert();
    PostModal postModal;

    public void postPost(PostModal postModal, String uri, int status) {
        String response = post(gson.toJson(postModal), uri, status);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        int userId = jsonObject.get("userId").getAsInt();
        int id = jsonObject.get("id").getAsInt();
        String title = jsonObject.get("title").getAsString();
        String body = parseString(response)
                .getAsJsonObject().get("body").getAsString();
        softAssert.assertEquals(userId, postModal.getUserId(), "UserId are not equal");
        softAssert.assertEquals(id, postModal.getId(), "Ids are not equal");
        softAssert.assertEquals(title, postModal.getTitle(), "Title are not equal");
        softAssert.assertEquals(body, postModal.getBody(), "Id Body are not equal");
        softAssert.assertAll();
    }

    @SneakyThrows
    @Step("Get all posts")
    public void getPosts1(String endUri, int status) {
        String response = get(endUri, status);
        System.out.println(response);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);
        System.out.println("..." + jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            Assert.assertTrue(jsonObject.get("id").getAsInt() - i == 1, "Post can not be found 404");
        }
    }

    @SneakyThrows
    @Step("Get one post")
    public void getOnePost2(String endUri, int userID, int id, int status) {
        String response = get(endUri, userID, id, status);
        System.out.println(response);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        endUri = jsonObject.get("endUri").getAsString();
        userID = jsonObject.get("userID").getAsInt();
        id = jsonObject.get("id").getAsInt();
        status = jsonObject.get("status").getAsInt();
        softAssert.assertEquals(endUri, postModal.getEndUri(), "EndUri are not equal");
        softAssert.assertEquals(userID, postModal.getUserId(), "UserIds are not equal");
        softAssert.assertEquals(userID == 10, "Post can not be found 404");
        softAssert.assertEquals(id, postModal.getId(), "Ids are not equal");
        softAssert.assertEquals(id == 99, "Post can not be found 404");
        softAssert.assertEquals(status, postModal.getStatus(), "Status are not equal");
        softAssert.assertAll();
    }

    @SneakyThrows
    @Step("Get void post")
    public void getVoidPost3(String endUri, int id, int status) {
        String response = get(endUri, id, status);
        System.out.println(response);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        endUri = jsonObject.get("endUri").getAsString();
        id = jsonObject.get("id").getAsInt();
        status = jsonObject.get("status").getAsInt();
        softAssert.assertEquals(endUri, postModal.getEndUri(), "EndUri are not equal");
        softAssert.assertEquals(id = 150, postModal.getId(), "Post can not be found 404");
        softAssert.assertEquals(status, postModal.getStatus(), "Status are not equal");
        softAssert.assertAll();
    }
}