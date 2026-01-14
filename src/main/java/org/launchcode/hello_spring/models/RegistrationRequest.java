package org.launchcode.hello_spring.models;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String emailAddress;
}
