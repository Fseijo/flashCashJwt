package com.seijo.flashCash.controller;

import com.seijo.flashCash.auth.AuthenticationRequest;
import com.seijo.flashCash.auth.AuthenticationResponse;
import com.seijo.flashCash.auth.AuthenticationService;
import com.seijo.flashCash.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Post with PostMan http://localhost:8080/api/v1/auth/register to create a new user
     * @param request
     * @return JWT Token and save a user in the BDD
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Post with PostMan http://localhost:8080/api/v1/auth/authenticate to authenticate an existing user from BDD
     * @param request
     * @return JWT Token created when user was saved in BDD
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
