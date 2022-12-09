package com.techelevator.controller;

import com.techelevator.dao.FriendDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.friends.FriendsPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/friends")
public class FriendController {

    //TODO: figure out why I have to use autowired and not a constructor
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private UserDao userDao;


    public FriendController(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    @PostMapping("/request/{requestId}")
    public void makeFriendRequest(Principal principal, @PathVariable Integer requestId) {
        Integer userId = userDao.findIdByUsername(principal.getName());
        friendDao.createFriendRequest(userId, requestId);
    }


    @GetMapping("/{userId}")
    public FriendsPageDto getFriendsPageByUserId(@PathVariable Integer userId) {
        return friendDao.getFriendPageByUserId(userId);
    }

    @PutMapping("/accept/{requestId}")
    public void acceptFriendRequest() {

    }


}
