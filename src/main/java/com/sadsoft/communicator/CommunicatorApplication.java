package com.sadsoft.communicator;

import com.sadsoft.communicator.dao.UserRepository;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.LogDto;
import com.sadsoft.communicator.model.dto.RegDto;
import com.sadsoft.communicator.service.ContactsBookService;
import com.sadsoft.communicator.service.AuthService;
import com.sadsoft.communicator.service.ConversationService;
import com.sadsoft.communicator.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommunicatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(CommunicatorApplication.class, args);
    }


    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           AuthService authService,
                           RoleService roleService,
                           ContactsBookService contactsBookService,
                           ConversationService conversationService) {

        return args -> {

            User currentUser;

            roleService.generateRoles();

            authService.signUp(new RegDto("bulzaj", "secret", "secret"));
            authService.signUp(new RegDto("fasla", "secret", "secret"));
            authService.signUp(new RegDto("miska", "secret", "secret"));
            authService.signUp(new RegDto("kusy", "secret", "secret"));
            authService.signUp(new RegDto("test1", "secret", "secret"));
            authService.signUp(new RegDto("test2", "secret", "secret"));
            authService.signUp(new RegDto("test3", "secret", "secret"));
            authService.signUp(new RegDto("test4", "secret", "secret"));

            currentUser = userRepository.findByUsername("bulzaj").get();
            User receiver = userRepository.findByUsername("fasla").get();
            User newSender = userRepository.findByUsername("miska").get();
            User newReceiver = userRepository.findByUsername("kusy").get();

            contactsBookService.addUserToContactsBook(currentUser, "fasla");
            contactsBookService.addUserToContactsBook(currentUser, "miska");
            contactsBookService.addUserToContactsBook(currentUser, "bulzaj");

            currentUser = userRepository.findByUsername("fasla").get();
            contactsBookService.addUserToContactsBook(currentUser, "bulzaj");
            contactsBookService.addUserToContactsBook(currentUser, "miska");

            currentUser = userRepository.findByUsername("miska").get();
            contactsBookService.addUserToContactsBook(currentUser, "bulzaj");
            contactsBookService.addUserToContactsBook(currentUser, "fasla");
        };
    }
}
