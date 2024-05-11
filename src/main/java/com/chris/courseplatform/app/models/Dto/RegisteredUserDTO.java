package com.chris.courseplatform.app.models.Dto;


import com.chris.courseplatform.app.models.Role;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter @Setter
public class RegisteredUserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
    private String token;
}
