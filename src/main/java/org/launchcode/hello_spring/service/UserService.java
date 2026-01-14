package org.launchcode.hello_spring.service;

import org.launchcode.hello_spring.models.RegistrationRequest;
import org.launchcode.hello_spring.models.User;
import org.launchcode.hello_spring.models.dto.UserDto;

import java.util.List;

public interface UserService {

    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUser(User user);
}
