package com.sadsoft.communicator.controller.auth;

import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.RegLogDto;
import com.sadsoft.communicator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody RegLogDto input) {

        return authService.signUp(input);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity signin(@Valid @RequestBody RegLogDto input) {

        return authService.siginIn(input);
    }
}
