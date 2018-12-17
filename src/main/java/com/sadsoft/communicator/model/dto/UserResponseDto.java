package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private Date createdAt;

    public UserResponseDto(User user) {
        id = user.getId();
        username = user.getUsername();
        createdAt = user.getCreatedAt();
    }
}
