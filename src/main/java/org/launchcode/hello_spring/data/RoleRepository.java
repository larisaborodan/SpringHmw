package org.launchcode.hello_spring.data;


import org.launchcode.hello_spring.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
