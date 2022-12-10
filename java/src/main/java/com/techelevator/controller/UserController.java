package com.techelevator.controller;


import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.FriendDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserDao userDao;
    private final FriendDao friendDao;
    private final CollectionDao collectionDao;

    public UserController(UserDao userDao, FriendDao friendDao, CollectionDao collectionDao) {
        this.userDao = userDao;
        this.friendDao = friendDao;
        this.collectionDao = collectionDao;
    }


    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Integer userId) {
        UserDto user = userDao.getUserDtoById(userId);
        user.setFriends(friendDao.getFriendsById(userId));
        user.setCollections(collectionDao.listCollectionsByUserId(userId));
        user.setBase(collectionDao.getBaseCollectionByUserId(user.getId()));
        return user;
    }

    @GetMapping("")
    public List<UserDto> getUsers(@RequestParam(name = "username") String username) {
        return userDao.findAllByUsername(username);
    }

    @GetMapping("/id/{username}")
    public Integer getUserIdByUsername(@PathVariable String username) {
        return userDao.findIdByUsername(username);
    }

}
