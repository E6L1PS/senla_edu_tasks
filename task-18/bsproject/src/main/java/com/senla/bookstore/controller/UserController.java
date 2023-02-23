package com.senla.bookstore.controller;


import com.senla.bookstore.dto.UserDto;
import com.senla.bookstore.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private IUserService userService;


    @Autowired
    private UserDetailsService securityService;


    @PostMapping("/register")
    public void registration(@RequestBody UserDto userDto) {
        log.info("Register {}", userDto);

        userService.save(userDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserDto userDto) {
        securityService.loadUserByUsername(userDto.getUsername());
    }

}
