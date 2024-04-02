package jsonplaceholder.adapters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import framework.BaseAdapter;
import io.qameta.allure.Step;
import jsonplaceholder.modalse.UserModal;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.google.gson.JsonParser.parseString;

public class UsersAdapter extends BaseAdapter {
    SoftAssert softAssert = new SoftAssert();
    UserModal userModal;

    public void userPost(UserModal userModal, String uri, int status) {
        String response = post(gson.toJson(userModal), uri, status);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        String name = String.valueOf(jsonObject.get("name").getAsInt());
        int id = jsonObject.get("id").getAsInt();
        String userName = jsonObject.get("userName").getAsString();
        String email = parseString(response)
                .getAsJsonObject().get("email").getAsString();
        softAssert.assertEquals(name, userModal.getName(), "Names are not equal");
        softAssert.assertEquals(id, userModal.getId(), "Ids are not equal");
        softAssert.assertEquals(userName, userModal.getUserName(), "UserNames are not equal");
        softAssert.assertEquals(email, userModal.getEmail(), "Emails are not equal");
        softAssert.assertAll();
    }

    @SneakyThrows
    @Step("Get all users posts")
    public void getUsersPosts(String endUri, int status) {
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
    @Step("Get one user post")
    public void getOneUserPost5(String endUri, int userID, int id, int status) {
        String response = get(endUri, userID, id, status);
        System.out.println(response);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        String name = String.valueOf(jsonObject.get("name").getAsInt());
        id = jsonObject.get("id").getAsInt();
        String userName = jsonObject.get("userName").getAsString();
        String email = parseString(response)
                .getAsJsonObject().get("email").getAsString();
        softAssert.assertEquals(name, userModal.getName(), "Names are not equal");
        softAssert.assertEquals(id, userModal.getId(), "Ids are not equal");
        softAssert.assertEquals(userID == 5, "Post can not be found 404");
        softAssert.assertEquals(userName, userModal.getUserName(), "UserNames are not equal");
        softAssert.assertEquals(email, userModal.getEmail(), "Emails are not equal");
        softAssert.assertEquals(endUri, userModal.getEndUri(), "EndUri are not equal");
        softAssert.assertEquals(status, userModal.getStatus(), "Status are not equal");
        softAssert.assertAll();
    }
}
