package se.web.store.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AdminUserDTO {

    /** Error Messages **/
    public static final String EMAIL_ERROR      = "Email not valid";
    public static final String PASSWORD_ERROR   = "At least one digit [0-9]\n" +
            "At least one lowercase character [a-z]\n" +
            "At least one uppercase character [A-Z]\n" +
            "At least one special character [*.!@#$%^&(){}[]:;<>,.?/~_+-=|\\]\n" +
            "At least 8 characters in length, but no more than 32.";

    /** REGEX **/
    public static final String EMAIL_RULE       = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    public static final String PASSWORD_RULE    = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]).{8,32}$";

//@Email(regexp = EMAIL_RULE, message = EMAIL_ERROR)
    @NotBlank(message = "Email is required")
    private String username;

    //@Pattern(regexp = PASSWORD_RULE)
    @NotBlank(message = "This field is required")
    @Size(min = 8, max = 32)
    private String password;

    private String role;

    /** Getters and setters **/

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AdminUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
