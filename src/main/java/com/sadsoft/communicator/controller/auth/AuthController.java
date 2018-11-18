package com.sadsoft.communicator.controller.auth;

import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.AuthResponseDto;
import com.sadsoft.communicator.model.dto.RegLogDto;
import com.sadsoft.communicator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody RegLogDto input) {

        User user = authService.signUp(input);

        if (user != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/users/{username}")
                    .buildAndExpand(user.getUsername()).toUri();

            return ResponseEntity.created(location).body("User registered successfully!");
        }

        return ResponseEntity.badRequest().body("Username already taken!");

    }


    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity signin(@Valid @RequestBody RegLogDto input) {

        AuthResponseDto authResponseDto = authService.siginIn(input);

        return ResponseEntity.ok(authResponseDto);
    }
}
