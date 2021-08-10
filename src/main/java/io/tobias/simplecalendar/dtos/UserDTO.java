package io.tobias.simplecalendar.dtos;
public class UserDTO {

    private String password;
    private String username;
    private String email;


    public UserDTO(String password, String username, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }


    public UserDTO() {
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}