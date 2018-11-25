package com.sadsoft.communicator.service;

import com.sadsoft.communicator.config.JwtTokenProvider;
import com.sadsoft.communicator.dao.ContactsBookRepository;
import com.sadsoft.communicator.dao.RoleRepository;
import com.sadsoft.communicator.dao.UserRepository;
import com.sadsoft.communicator.exceptions.PasswordDoesNotMatchException;
import com.sadsoft.communicator.exceptions.UserDoesNotExistsException;
import com.sadsoft.communicator.model.ContactsBook;
import com.sadsoft.communicator.model.Role;
import com.sadsoft.communicator.model.RoleName;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.AuthResponseDto;
import com.sadsoft.communicator.model.dto.RegLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ContactsBookRepository contactsBookRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    BCryptPasswordEncoder encoder;

    public User signUp(RegLogDto input) {

        User user;
        ContactsBook contactsBook;

        if (checkUsernameAccessibility(input.getUsername())) {

            contactsBook = new ContactsBook();

            user = new User();
            user.setUsername(input.getUsername());
            user.setPassword(encoder.encode(input.getPassword()));

            Role role = roleRepository.findByRolename(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role not set."));

            user.setRoles(Collections.singleton(role));
            user.setCreatedAt(new Date());
            user.setContactsBook(contactsBook);

            contactsBookRepository.save(contactsBook);
            User result = userRepository.save(user);

            return result;
        }
        return null;
    }

    public AuthResponseDto siginIn(RegLogDto input) {

        User user = userRepository.findByUsername(input.getUsername()).orElseThrow(
                () -> new UserDoesNotExistsException("User: " + input.getUsername() + " does not exists")
        );

        if (!encoder.matches(input.getPassword(), user.getPassword())) {
            throw new PasswordDoesNotMatchException("Wrong password");
        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getUsername(),
                            input.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);


            String jwt = tokenProvider.generateToken(authentication);
            return new AuthResponseDto(jwt);
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User:" + username + " Does not exists")
        );
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
