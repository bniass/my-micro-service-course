package com.ecole221.microsdervices.course.api.gateway.keycloak;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String userName;
    private String emailId;
    private String password;
    private String firstname;
    private String lastName;
    private List<String> roles;
}
