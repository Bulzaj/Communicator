package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.model.Conversation;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.ConversationResponseDto;
import com.sadsoft.communicator.model.dto.SimpleMessageResponseDto;
import com.sadsoft.communicator.service.AuthService;
import com.sadsoft.communicator.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversation/history")
@PreAuthorize("hasRole('ROLE_USER')")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ConversationHistoryController {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/{receiversName}")
    public ResponseEntity<?> getConversation(@PathVariable String receiversName) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User sender = authService.geCurrentUser(authentication);
        User receiver = authService.getUserByUsername(receiversName);

        Conversation conversation = conversationService.getMessages(sender, receiver);

        if (conversation != null) {
            return ResponseEntity.ok(new ConversationResponseDto(conversation));
        }

        return  ResponseEntity.ok(new SimpleMessageResponseDto("You have not any messages yet"));
    }
}
