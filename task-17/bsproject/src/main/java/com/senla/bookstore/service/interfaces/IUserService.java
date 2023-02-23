package com.senla.bookstore.service.interfaces;

import com.senla.bookstore.dto.UserDto;
import com.senla.bookstore.model.User;

public interface IUserService {

    User findByUsername(String username);

    void save(UserDto userDTO);

}
