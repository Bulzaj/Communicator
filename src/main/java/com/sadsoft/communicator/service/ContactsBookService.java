package com.sadsoft.communicator.service;

import com.sadsoft.communicator.dao.ContactsBookRepository;
import com.sadsoft.communicator.dao.UserRepository;
import com.sadsoft.communicator.model.ContactsBook;
import com.sadsoft.communicator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ContactsBookService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactsBookRepository contactsBookRepository;

    @Autowired
    private AuthService authService;

    public User addUserToContactsBook(User currentUser, String newContactUsername) throws Exception {

        User newContact = userRepository.findByUsername(newContactUsername).orElseThrow(
                ()-> new UsernameNotFoundException(newContactUsername)
        );

        ContactsBook contactsBook = contactsBookRepository.findById(currentUser.getId()).orElseThrow(
                () -> new Exception("Contacts Book does not exists")
        );

        if (!currentUser.getUsername().equals(newContactUsername) &&
            !contactsBook.getContacts().contains(newContact)) {

            contactsBook.getContacts().add(newContact);
            contactsBookRepository.save(contactsBook);

            return newContact;
        }

        return null;
    }

    public User removeUserFromContactsBook (User currentUser, String contactsNametoRemove) throws Exception {

        User deletedContact = userRepository.findByUsername(contactsNametoRemove).orElseThrow(
                ()-> new UsernameNotFoundException(contactsNametoRemove)
        );

        ContactsBook contactsBook = contactsBookRepository.findById(currentUser.getId()).orElseThrow(
                () -> new Exception("Contacts Book does not exists")
        );

        if (!currentUser.getUsername().equals(contactsNametoRemove) &&
                contactsBook.getContacts().contains(deletedContact)) {

            contactsBook.getContacts().remove(deletedContact);
            contactsBookRepository.save(contactsBook);

            return deletedContact;
        }

        return null;
    }

    public User getContact(User currentUser, String contactsName) throws Exception {

        ContactsBook contactsBook = contactsBookRepository.findById(currentUser.getId()).orElseThrow(
                () -> new Exception("Contacts Book does not exists")
        );

        User contact = authService.getUserByUsername(contactsName);

        if (contactsBook.getContacts().contains(contact)) {
            for (User result: contactsBook.getContacts()) {
                if (result.equals(contact)) return contact;
            }
        }
        return null;
    }

    /*public Set<User> getAllContacts(User currentUser) {

        return userRepository.findByUsername(currentUser.getUsername())
                .get()
                .getContactsBook()
                .getContacts();
    }*/
}
