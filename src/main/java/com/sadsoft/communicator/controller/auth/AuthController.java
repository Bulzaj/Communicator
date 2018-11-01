package com.sadsoft.communicator.controller.auth;

import com.sadsoft.communicator.model.dto.RegLogModel;
import com.sadsoft.communicator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody RegLogModel input) {

        return authService.signUp(input);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody RegLogModel input) {

        return authService.siginIn(input);
    }
}
