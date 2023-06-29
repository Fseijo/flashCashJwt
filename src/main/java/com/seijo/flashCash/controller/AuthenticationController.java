package com.seijo.flashCash.controller;

import com.seijo.flashCash.auth.AuthenticationRequest;
import com.seijo.flashCash.auth.AuthenticationResponse;
import com.seijo.flashCash.auth.AuthenticationService;
import com.seijo.flashCash.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    ) {
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
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/")
    public ModelAndView index(Model model){
        return new ModelAndView("index");
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm(){
        return new ModelAndView("signup", "registerForm", new RegisterRequest());
    }

    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute ("registerForm") RegisterRequest request) {
        authenticationService.register(request);
        return new ModelAndView("signin");
    }


}
