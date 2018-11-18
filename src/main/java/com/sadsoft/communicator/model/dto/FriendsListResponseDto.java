package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FriendsListResponseDto {

    private Set<User> contacts = new HashSet<>();

}
