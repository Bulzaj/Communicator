package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.Message;
import lombok.Data;

@Data
public class MessageCreatedResponseDto {

    private final String communication = "Message was sended successfully";
    private String messageBody;

    public MessageCreatedResponseDto(Message message) {
        this.messageBody = message.getMessageBody();
    }
}
