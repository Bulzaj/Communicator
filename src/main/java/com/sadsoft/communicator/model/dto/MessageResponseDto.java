package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Data
@AllArgsConstructor
public class MessageResponseDto {

    @Autowired
    private BCryptPasswordEncoder encoder;

    private String sendersName;
    private String messageBody;
    private String conversationsUniqueName;
    private Date createdAt;

    public MessageResponseDto(Message message) {
        this.sendersName = message.getSender().getUsername();
        this.messageBody = message.getMessageBody();
        this.conversationsUniqueName = message.getConversation().getUniqueConversationsName();
        this.createdAt = message.getCreatedAt();
    }
}
