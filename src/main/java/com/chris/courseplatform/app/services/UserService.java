package com.chris.courseplatform.app.services;

import com.chris.courseplatform.app.models.Dto.RegisteredUser;
import com.chris.courseplatform.app.models.Dto.UserDTO;
import com.chris.courseplatform.app.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String name);
    User registerUser(RegisteredUser registeredUser);

    UserDTO getUser(Long id);
}
