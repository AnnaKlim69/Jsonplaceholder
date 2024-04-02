package jsonplaceholder.modalse;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserModal {
    int id;
    int status;
    String name;
    String userName;
    String email;
    String address;
    String phone;
    String website;
    String company;
    String endUri;

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getName() { return name;  }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getCompany() {
        return company;
    }

    public String getEndUri() {
        return endUri;
    }
}
