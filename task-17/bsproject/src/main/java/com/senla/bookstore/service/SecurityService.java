package com.senla.bookstore.service;

import com.senla.bookstore.repository.interfaces.IUserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("securityService")
public class SecurityService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @SneakyThrows
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
    }
}
