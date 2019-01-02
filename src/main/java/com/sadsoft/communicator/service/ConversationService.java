package com.sadsoft.communicator.service;

import com.sadsoft.communicator.dao.ConversationRepository;
import com.sadsoft.communicator.dao.MessageRepository;
import com.sadsoft.communicator.exceptions.ConversationDoseNotExistsException;
import com.sadsoft.communicator.model.Conversation;
import com.sadsoft.communicator.model.Message;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.ConversationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class ConversationService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    private Conversation getConversationByConversationsName(String conversationsName) {
        return conversationRepository.findByUniqueConversationsName(conversationsName).orElseThrow(
                () -> new ConversationDoseNotExistsException(conversationsName)
        );
    }

    public Message saveMessage(User sender, String messageBody, User receiver) {

        Message message = new Message();
        message.setSender(sender);
        message.setMessageBody(messageBody);
        message.setCreatedAt(new Date());

        Conversation conversation = initConversation(sender, receiver);
        conversation.sendMessage(message);

        conversationRepository.save(conversation);
        messageRepository.save(message);

        return message;
    }

    public Conversation getConversation(User currentUser, User receiver) {

        String conversationsName = conversationsNameGenerator(currentUser, receiver);

        return getConversationByConversationsName(conversationsName);
    }

    public Conversation getConversation(String conversationsName) {
        return conversationRepository.findByUniqueConversationsName(conversationsName).orElseThrow(
                () -> new ConversationDoseNotExistsException(conversationsName)
        );
    }

    public Conversation initConversation(@NotNull User participant1, @NotNull User participant2) {
        String conversationsName = conversationsNameGenerator(participant1, participant2);
        Conversation conversation = conversationRepository.findByUniqueConversationsName(conversationsName).orElseGet(
                () -> {
                    return new Conversation(conversationsName, participant1, participant2);
                }
        );
        return conversation;
    }

    public String conversationsNameGenerator(@NotNull  User participant1, @NotNull  User participant2) {

        String conversationName = "";
        List<String> tmp = new ArrayList<>();

        tmp.add(participant1.getUsername());
        tmp.add(participant2.getUsername());
        Collections.sort(tmp);

        for (String x: tmp) {
            conversationName += x;
        }

        return conversationName;
    }
}
