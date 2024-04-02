package jsonplaceholder.tests;

import com.github.javafaker.Faker;
import framework.PropertyReader;
import framework.TestListener;
import jsonplaceholder.adapters.PostsAdapter;
import jsonplaceholder.modalse.PostModal;
import lombok.SneakyThrows;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static framework.PropertyReader.getIntProperty;
import static framework.PropertyReader.getProperty;

@Listeners(TestListener.class)
public class PostsTests {
    PropertyReader propertyReader = new PropertyReader("config.properties");
    private Faker faker;

    @SneakyThrows
    @Test
    public void getAllPosts() {
        PostsAdapter postsAdapter = new PostsAdapter();
        postsAdapter.getPosts1(getProperty("END_URI_POSTS"), getIntProperty("status200"));
    }

    @SneakyThrows
    @Test
    public void getOnePost() {
        PostsAdapter postsAdapter = new PostsAdapter();
        postsAdapter.getOnePost2(getProperty("END_URI_POSTS"), 99, 10, getIntProperty("status200"));
    }

    @SneakyThrows
    @Test
    public void getVoidPost() {
        PostsAdapter postsAdapter = new PostsAdapter();
        postsAdapter.getVoidPost3(getProperty("END_URI_POSTS"), 150, getIntProperty("status404"));
    }

    @SneakyThrows
    @Test
    public void createNewPost4() {
        Faker faker = new Faker();
        PostModal postModal = PostModal.builder()
                .userId(1)
                .id(102)
                .title(faker.country().countryCode2())
                .body(faker.university().name())
                .build();
        PostsAdapter postsAdapter = new PostsAdapter();
        postsAdapter.postPost(postModal, getProperty("END_URI_POSTS"), getIntProperty("status201"));
    }

    @SneakyThrows
    @Test
    public void createNewPost() {
        Faker faker = new Faker();
        PostModal postModal = PostModal.builder()
                .userId(1)
                .id(101)
                .title(faker.dog().name())
                .body(faker.cat().name())
                .build();
        PostsAdapter postsAdapter = new PostsAdapter();
        postsAdapter.postPost(postModal, getProperty("END_URI_POSTS"), getIntProperty("status201"));
    }
}