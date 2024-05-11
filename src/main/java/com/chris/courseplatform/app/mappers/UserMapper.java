package com.chris.courseplatform.app.mappers;

import com.chris.courseplatform.app.models.Dto.RegisteredUser;
import com.chris.courseplatform.app.models.Dto.RegisteredUserDTO;
import com.chris.courseplatform.app.models.Dto.UserDTO;
import com.chris.courseplatform.app.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles",ignore = true)
    @Mapping(target = "password",ignore = true)
    User registeredUserToUser(RegisteredUser registeredUser);

    @Mapping(target = "token", ignore = true)
    RegisteredUserDTO userToRegisteredUserDTO(User user);

    UserDTO userToUserDTO(User user);


}
