package com.destiny.project.a.api.result;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class LoginResult implements Serializable {
    private String token;

    private UserInfo userInfo;

    @Data
    public static class UserInfo implements Serializable {
        private String username;

        private String sex;

        private LocalDate birthday;

        private String address;
    }

}
