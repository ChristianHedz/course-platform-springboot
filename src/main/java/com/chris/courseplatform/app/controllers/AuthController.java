package com.chris.courseplatform.app.controllers;

import com.chris.courseplatform.app.models.Dto.*;
import com.chris.courseplatform.app.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class AuthController {

    private AuthenticationService authenticationService;

    @PostMapping("/user")
    public ResponseEntity<RegisteredUserDTO> registerUser(@RequestBody @Valid RegisteredUser registeredUser){
        RegisteredUserDTO response = authenticationService.registerUser(registeredUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("demo")
    public String prueba(){
        return "Hola Mundo";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserRequest userRequest){
        AuthResponse response = authenticationService.login(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("profile")
    public ResponseEntity<UserDTO> findLoggerUser(){
        UserDTO userDTO = authenticationService.findLoggerUser();
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

}
