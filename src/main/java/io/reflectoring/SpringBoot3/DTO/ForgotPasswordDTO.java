package io.reflectoring.SpringBoot3.DTO;

public class ForgotPasswordDTO {

    String password;

    public ForgotPasswordDTO(String password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}
