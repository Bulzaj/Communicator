package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping
    @Secured("ROLE_USER")
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    public ResponseEntity<User> getUserDetails(Authentication authentication) {

        User currentUser = authService.geCurrentUser(authentication);

        return ResponseEntity.ok(currentUser);
    }
}
