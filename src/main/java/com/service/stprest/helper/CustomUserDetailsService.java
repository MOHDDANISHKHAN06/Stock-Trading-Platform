package com.service.stprest.helper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.service.stprest.dao.UserDao;
import com.service.stprest.entities.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserDao userRepository;

    public CustomUserDetailsService(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          User user = userRepository.findById(email)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with email: "+ email));
          
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        if(user.isAdmin()) {
        	authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
        	authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), authorities);
    }
}
