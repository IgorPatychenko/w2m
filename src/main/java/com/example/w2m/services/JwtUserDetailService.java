package com.example.w2m.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username: w2m
        // password w2m
        return new User("w2m", "$2a$10$OF7vJ8pxmUxTI9wpJVCO/eBRrstjTaFI9BaxBng2p6txjobaRA71q", new ArrayList<>());
    }
}
