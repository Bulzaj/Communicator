package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.dao.UserRepository;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/conversation")
public class ConversationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationService conversationService;

    @Secured("ROLE_USER")
    @PostMapping("/sendTo/{receiver}")
    public ResponseEntity<?> sendMessage(Authentication currentUser,
                                         @RequestBody String messageBody,
                                         @PathVariable String receiver) {

        User receiverUser = userRepository.findByUsername(receiver).orElseThrow(
                () -> new UsernameNotFoundException(receiver)
        );

        return conversationService.sendMessage(currentUser, messageBody, receiverUser);
    }
}
