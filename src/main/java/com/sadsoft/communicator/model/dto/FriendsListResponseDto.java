package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.ContactsBook;
import com.sadsoft.communicator.model.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FriendsListResponseDto {

    private Set<ContactDto> contacts = new HashSet<>();

    public FriendsListResponseDto(ContactsBook contactsBook) {

        for (User user: contactsBook.getContacts()) {

            ContactDto contactDto = new ContactDto(user);
            contacts.add(contactDto);
        }
    }
}
