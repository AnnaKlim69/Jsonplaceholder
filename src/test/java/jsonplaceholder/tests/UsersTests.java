package jsonplaceholder.tests;

import framework.PropertyReader;
import framework.TestListener;
import jsonplaceholder.adapters.PostsAdapter;
import jsonplaceholder.adapters.UsersAdapter;
import lombok.SneakyThrows;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static framework.PropertyReader.getIntProperty;
import static framework.PropertyReader.getProperty;

@Listeners(TestListener.class)
public class UsersTests {
    PropertyReader propertyReader = new PropertyReader("config.properties");

    @SneakyThrows
    @Test
    public void getAllUsersPosts() {
        UsersAdapter usersAdapter = new UsersAdapter();
        usersAdapter.getUsersPosts(getProperty("END_URI_USERS"), getIntProperty("status200"));
    }

    @SneakyThrows
    @Test
    public void getOnePost() {
        UsersAdapter usersAdapter = new UsersAdapter();
       usersAdapter.getOneUserPost5(getProperty("END_URI_USERS"), 99, 10, getIntProperty("status200"));
    }
}