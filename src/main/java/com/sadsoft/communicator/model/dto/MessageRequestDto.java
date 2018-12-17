package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.Message;
import lombok.Data;

import java.util.Date;

@Data
public class MessageRequestDto {

    private String messageBody;
    private String sendersName;
    private Date createdAt;

    public MessageRequestDto(Message message) {
        messageBody = message.getMessageBody();
        sendersName = message.getSender().getUsername();
        createdAt = message.getCreatedAt();
    }
}
