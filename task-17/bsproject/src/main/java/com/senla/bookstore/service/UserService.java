package com.senla.bookstore.service;

import com.senla.bookstore.dto.UserDto;
import com.senla.bookstore.model.Role;
import com.senla.bookstore.model.User;
import com.senla.bookstore.repository.interfaces.IUserRepository;
import com.senla.bookstore.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Slf4j
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return (User) userRepository.findByUserName(username).orElseThrow();
    }

    @Transactional
    public void save(UserDto userDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .credentialsNonExpired(true)
                .authorities(Collections.singleton(Role.USER))
                .build();

        userRepository.create(user);
    }
}
