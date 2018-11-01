package com.sadsoft.communicator.service;

import com.sadsoft.communicator.config.JwtTokenProvider;
import com.sadsoft.communicator.dao.RoleRepository;
import com.sadsoft.communicator.dao.UserRepository;
import com.sadsoft.communicator.model.Role;
import com.sadsoft.communicator.model.RoleName;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.RegLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    BCryptPasswordEncoder encoder;

    public ResponseEntity<?> signUp(RegLogDto input) {

        User user;

        if (checkUsernameAccessibility(input.getUsername())) {

            user = new User();
            user.setUsername(input.getUsername());
            user.setPassword(encoder.encode(input.getPassword()));

            Role role = roleRepository.findByRolename(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role not set."));

            user.setRoles(Collections.singleton(role));
            user.setCreatedAt(new Date());

            User result = userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/users/{username}")
                    .buildAndExpand(result.getUsername()).toUri();

            return ResponseEntity.created(location).body("User registered successfully!");
        }
        return ResponseEntity.badRequest().body("Username already taken!");
    }

    public ResponseEntity<?> siginIn(RegLogDto input) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok("Bearer " + jwt);
    }

    public User geCurrentUser(Authentication authentication) {

        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        return user;
    }

    private boolean checkUsernameAccessibility(String username) {

        return !userRepository.existsByUsername(username);
    }


}
