package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.User;
import lombok.Data;

@Data
public class ContactDto {

    private Long id;
    private String username;

    public ContactDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
