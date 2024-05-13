package com.chris.courseplatform.app.controllers;

import com.chris.courseplatform.app.models.Dto.*;
import com.chris.courseplatform.app.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    private AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Register a new user in the platform")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User created successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RegisteredUser.class))
                    }),
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/user")
    public ResponseEntity<RegisteredUserDTO> registerUser(@RequestBody @Valid RegisteredUser registeredUser){
        RegisteredUserDTO response = authenticationService.registerUser(registeredUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "User login", description = "Authenticate a user and return an authentication token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User authenticated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserRequest userRequest){
        AuthResponse response = authenticationService.login(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get logged in user profile", description = "Return the profile of the currently authenticated user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User profile retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDTO.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name = "bearerToken")
    @PostMapping("profile")
    public ResponseEntity<UserDTO> findLoggerUser(){
        UserDTO userDTO = authenticationService.findLoggerUser();
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("demo")
    public String prueba(){
        return "Hola Mundo";
    }
}
