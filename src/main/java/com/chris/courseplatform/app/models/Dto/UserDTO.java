package com.chris.courseplatform.app.models.Dto;


import com.chris.courseplatform.app.models.Role;
import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}
