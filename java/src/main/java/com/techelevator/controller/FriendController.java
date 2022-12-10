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
@PreAuthorize("isAuthenticated()")
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
    public void createFriendRequest(Principal principal, @PathVariable Integer requestId) {
        Integer userId = userDao.findIdByUsername(principal.getName());
        if (friendDao.usersAreFriends(userId, requestId)) {
            throw new IllegalArgumentException("Users are already friends");
        }
        friendDao.createFriendRequest(userId, requestId);
    }


    @GetMapping("/{userId}")
    public FriendsPageDto getFriendsPageByUserId(@PathVariable Integer userId) {
        return friendDao.getFriendPageByUserId(userId);
    }

    @PostMapping("/accept/{requestId}")
    public void acceptFriendRequest(Principal principal, @PathVariable Integer requestId) {
        Integer userId = userDao.findIdByUsername(principal.getName());
        if (!friendDao.userIsRecipientOfRequest(userId, requestId)) {
            throw new IllegalArgumentException(String.format("User '%s' cannot accept request '%s'", principal.getName(), requestId));
        } else {
            friendDao.acceptFriendRequest(requestId);
        }
    }

    @PostMapping("/reject/{requestId}")
    public void rejectFriendRequest(Principal principal, @PathVariable Integer requestId) {
        Integer userId = userDao.findIdByUsername(principal.getName());
        if (!friendDao.userIsRecipientOfRequest(userId, requestId)) {
            throw new IllegalArgumentException(String.format("User '%s' cannot reject request '%s'", principal.getName(), requestId));
        } else {
            friendDao.rejectFriendRequest(requestId);
        }
    }


}
