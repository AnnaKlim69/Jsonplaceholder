package jsonplaceholder.modalse;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostModal {
    int userId;
    int id;
    int status;
    String title;
    String body;
    String endUri;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getEndUri() {
        return endUri;
    }

    public int getStatus() {
        return status;
    }
}