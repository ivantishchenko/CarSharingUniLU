package lu.tishchenko.jsf.beans.model;

import javax.enterprise.inject.Model;

/**
 * Created by Ivan on 04.01.2017.
 */
@Model
public class LoginModel {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}