package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.model.Message;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.MessageRequestDto;
import com.sadsoft.communicator.model.dto.MessageCreatedResponseDto;
import com.sadsoft.communicator.service.AuthService;
import com.sadsoft.communicator.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/conversation")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@PreAuthorize("hasRole('ROLE_USER')")
public class SendingMessagesController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ConversationService conversationService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequestDto messageRequestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User receiver = authService.getUserByUsername(messageRequestDto.getReceiversName(), "Receiver has not been choosen");
        User currentUser = authService.geCurrentUser(authentication);

        Message message = conversationService.sendMessage(currentUser, messageRequestDto.getMessageBody(), receiver);

        return ResponseEntity.ok(new MessageCreatedResponseDto(message));
    }
}
