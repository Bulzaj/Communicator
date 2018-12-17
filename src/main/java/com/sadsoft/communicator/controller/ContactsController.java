package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.model.ContactsBook;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.FriendsListResponseDto;
import com.sadsoft.communicator.model.dto.UserResponseDto;
import com.sadsoft.communicator.service.AuthService;
import com.sadsoft.communicator.service.ContactsBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/contacts")
@PreAuthorize("hasRole('ROLE_USER')")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ContactsController {

    @Autowired
    private ContactsBookService contactsBookService;

    @Autowired
    private AuthService authService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<FriendsListResponseDto> getAllContacts(Authentication authentication) {

        User currentUser = authService.geCurrentUser(authentication);
        ContactsBook contactsBook = currentUser.getContactsBook();

        FriendsListResponseDto friendsList = new FriendsListResponseDto(contactsBook);

        return ResponseEntity.ok(friendsList);
    }

    @GetMapping(produces = "application/json")
    @RequestMapping("/{contactsName}")
    public ResponseEntity<UserResponseDto> getContact(@PathVariable String contactsName) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = authService.geCurrentUser(authentication);

        try {
            return ResponseEntity.ok(new UserResponseDto(contactsBookService.getContact(currentUser, contactsName)));
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createNewContact(Authentication authentication, @RequestBody String input) {

        User currentUser = authService.geCurrentUser(authentication);

        try {
            contactsBookService.addUserToContactsBook(currentUser, input);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/contacts/{username}")
                    .buildAndExpand(input)
                    .toUri();

            return ResponseEntity.created(location).body("New receiver added succesfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Somethings go wrong :(");
    }

    @DeleteMapping
    public ResponseEntity<?> removeContact (Authentication authentication, @RequestBody String input) {

        User currentUser = authService.geCurrentUser(authentication);

        try {
            User deletedUser = contactsBookService.removeUserFromContactsBook(currentUser, input);
            return ResponseEntity.ok(deletedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Somethings go wrong :(");
    }
}
