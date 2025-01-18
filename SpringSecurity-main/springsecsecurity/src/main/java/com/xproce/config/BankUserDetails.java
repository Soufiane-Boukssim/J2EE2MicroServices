package com.xproce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankUserDetails implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String login;
        String password;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.size() == 0) {
            throw new UsernameNotFoundException(username + " Does Not Exist ... " );
        } else {
            login = customers.get(0).getEmail();
            password = customers.get(0).getPwd();
            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        }
        return new User(login, password, authorities);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}