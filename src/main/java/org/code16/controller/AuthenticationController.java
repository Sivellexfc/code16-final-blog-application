package org.code16.controller;

import lombok.RequiredArgsConstructor;
import org.code16.dto.request.AuthenticationRequest;
import org.code16.dto.response.AuthenticationResponse;
import org.code16.dto.request.RegisterRequest;
import org.code16.services.entity.UserService;
import org.code16.services.security.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        if(!userService.findUserByUsername(request.getUsername()).isPresent())
            return ResponseEntity.ok(authenticationService.register(request));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
