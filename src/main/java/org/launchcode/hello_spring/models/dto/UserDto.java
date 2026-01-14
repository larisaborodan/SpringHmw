package org.launchcode.hello_spring.models.dto;

import lombok.Builder;
import java.util.List;
import java.time.LocalDate;

@Builder
public record UserDto(
        String username,
        List<RoleDto> roles,
        String firstName,
        String lastName,
        String emailAddress) {}
