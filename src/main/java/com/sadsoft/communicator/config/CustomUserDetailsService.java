package com.sadsoft.communicator.config;

import com.sadsoft.communicator.dao.UserRepository;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("User: " + s + " does not exists.")
        );

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User with id: " + id + " does not exists")
        );

        return UserPrincipal.create(user);
    }
}
