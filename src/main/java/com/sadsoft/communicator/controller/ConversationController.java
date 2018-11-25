package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.dao.UserRepository;
import com.sadsoft.communicator.model.Message;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.service.AuthService;
import com.sadsoft.communicator.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/conversation")
public class ConversationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ConversationService conversationService;

    @MessageMapping("/send-message")
    @SendTo("/message/send")
    public Message sendMessage(Authentication authentication, String messageBody, String receiversName) {

        User currentUser = authService.geCurrentUser(authentication);
        User receiver = authService.getUserByUsername(receiversName);

        return conversationService.sendMessage(currentUser, messageBody, receiver);
    }
}
