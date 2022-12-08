package com.techelevator.dao;

import com.techelevator.model.UserDto;
import com.techelevator.model.login.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    List<UserDto> findAllByUsername(String username);

    User getUserById(int userId);

    UserDto getUserDtoById(Integer userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);
}
