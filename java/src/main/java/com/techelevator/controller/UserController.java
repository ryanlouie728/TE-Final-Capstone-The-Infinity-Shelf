package com.techelevator.controller;


import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.FriendDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
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
        return user;
    }
}
