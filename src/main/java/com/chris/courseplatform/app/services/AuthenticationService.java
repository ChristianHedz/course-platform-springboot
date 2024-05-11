package com.chris.courseplatform.app.services;

import com.chris.courseplatform.app.exceptions.ResourceNotFoundException;
import com.chris.courseplatform.app.mappers.UserMapper;
import com.chris.courseplatform.app.models.Dto.*;
import com.chris.courseplatform.app.models.Jwt;
import com.chris.courseplatform.app.models.User;
import com.chris.courseplatform.app.repositories.JwtRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private JwtRepository tokenRepository;
    private UserMapper userMapper;
    private UserService userService;
    private AuthenticationManager authManager;
    private JwtService jwtService;
    private static final String USERNAME = "username";

    @Transactional
    public RegisteredUserDTO registerUser(RegisteredUser registeredUser){
        User user = userService.registerUser(registeredUser);
        String jwt = jwtService.generateToken(user,generateExtraClaims(user));
        saveToken(jwt,user);
        RegisteredUserDTO registeredUserDTO = userMapper.userToRegisteredUserDTO(user);
        registeredUserDTO.setToken(jwt);
        return registeredUserDTO;
    }

    private void saveToken(String jwt,User user) {
        Jwt token = new Jwt();
        token.setUser(user);
        token.setToken(jwt);
        token.setExpiration(jwtService.extractExpiration(jwt));
        token.setValid(true);
        tokenRepository.save(token);
    }

    private Map<String,Object> generateExtraClaims(User user) {
        return Map.of(
                USERNAME,user.getUsername(),
                "authorities",user.getAuthorities()
        );
    }

    public AuthResponse login(UserRequest userRequest) {
        Authentication auth =  new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(),userRequest.getPassword()
        );
        authManager.authenticate(auth);
        User user = userService.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("user",USERNAME,userRequest.getUsername()));
        String jwt = jwtService.generateToken(user,generateExtraClaims(user));
        saveToken(jwt,user);
        return new AuthResponse(jwt);
    }

    public UserDTO findLoggerUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken){
            throw new AuthenticationCredentialsNotFoundException("El usuario no esta autenticado ");
        }
        String user =  auth.getName();
        User authUser  = userService.findByUsername(user).orElseThrow(() -> new ResourceNotFoundException("user",USERNAME,user));
        return userMapper.userToUserDTO(authUser);
    }

}
