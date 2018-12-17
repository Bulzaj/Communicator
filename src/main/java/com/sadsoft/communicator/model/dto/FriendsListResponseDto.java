package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.ContactsBook;
import com.sadsoft.communicator.model.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FriendsListResponseDto {

    private Set<UserResponseDto> contacts = new HashSet<>();

    public FriendsListResponseDto(ContactsBook contactsBook) {

        for (User user: contactsBook.getContacts()) {

            UserResponseDto contactDto = new UserResponseDto(user);
            contacts.add(contactDto);
        }
    }
}
