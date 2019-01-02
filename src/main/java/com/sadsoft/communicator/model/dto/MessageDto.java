package com.sadsoft.communicator.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sadsoft.communicator.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Data
@AllArgsConstructor
public class MessageDto {

    /*@Autowired
    @JsonIgnore
    private BCryptPasswordEncoder encoder*/;

    private String sendersName;
    private String messageBody;
    private Date createdAt;

    public MessageDto(Message message) {
        this.sendersName = message.getSender().getUsername();
        this.messageBody = message.getMessageBody();
        this.createdAt = message.getCreatedAt();
    }
}
