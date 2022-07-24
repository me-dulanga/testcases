package com.dulanga.endgame.service;


import com.dulanga.endgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.dulanga.endgame.model.User> userOp = userRepository.findByUsername(username);
        if (!userOp.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        com.dulanga.endgame.model.User modelUser = userOp.get();
        UserDetails user = User.withUsername(modelUser.getUsername()).password(modelUser.getPassword()).authorities("USER").build();

//        System.out.println(new BCryptPasswordEncoder().encode("password"));
//        UserDetails user = User.withUsername("huba").password(new BCryptPasswordEncoder().encode("password")).authorities("USER").build();
        return user;
    }
}