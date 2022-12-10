package com.techelevator.dao;

import com.techelevator.model.friends.FriendDto;
import com.techelevator.model.friends.FriendRequestDto;
import com.techelevator.model.friends.FriendsPageDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFriendDao implements FriendDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcFriendDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public void createFriendRequest(Integer userId, Integer requestId) {
        String sql =
                "INSERT INTO friend_request (to_id, from_id) " +
                "VALUES (?, ?);";
        jdbcTemplate.update(sql, requestId, userId);
    }

    @Override
    public List<FriendDto> getFriendsById(Integer userId) {
        String sql =
                "SELECT user_id, friend_id " +
                "FROM user_friend " +
                "WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return friendListMapper(rowSet);
    }

    @Override
    public FriendDto getFriendById(Integer userId, Integer friendId) {
        String sql =
                "SELECT user_id, friend_id " +
                "FROM user_friend " +
                "WHERE user_id = ? " +
                "AND friend_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, friendId);
        if (rowSet.next()) {
            return friendMapper(rowSet);
        }
        return null;
    }

    @Override
    public Boolean userIsRecipientOfRequest(Integer userId, Integer requestId) {
        String sql =
                "SELECT to_id " +
                "FROM friend_request " +
                "WHERE request_id = ?;";
        Integer toId = jdbcTemplate.queryForObject(sql, Integer.class, requestId);
        return toId == userId;
    }

    @Override
    public Boolean userIsSenderOfRequest(Integer userId, Integer requestId) {
        String sql =
                "SELECT from_id " +
                "FROM friend_request " +
                "WHERE request_id = ?;";
        Integer fromId = jdbcTemplate.queryForObject(sql, Integer.class, requestId);
        return fromId == userId;
    }

    @Override
    public Boolean usersAreFriends(Integer userOne, Integer userTwo) {
        String sql =
                "SELECT user_id " +
                "FROM user_friend " +
                "WHERE user_id = ? " +
                "AND friend_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userOne, userTwo);
        return rowSet.next();
    }

    @Override
    public List<FriendRequestDto> getToRequestsByUserId(Integer userId) {
        String sql =
                "SELECT request_id, to_id, from_id, status " +
                "FROM friend_request " +
                "WHERE to_id = ? " +
                "AND status = 'pending';";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return friendRequestListMapper(rowSet);
    }

    @Override
    public List<FriendRequestDto> getFromRequestsByUserId(Integer userId) {
        String sql =
                "SELECT request_id, to_id, from_id, status " +
                "FROM friend_request " +
                "WHERE from_id = ? " +
                "AND status = 'pending';";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return friendRequestListMapper(rowSet);
    }

    @Override
    public void acceptFriendRequest(Integer requestId) {
        String sql =
                "UPDATE friend_request " +
                "SET status = 'accepted' " +
                "WHERE request_id = ? " +
                "RETURNING to_id, from_id;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, requestId);
        rowSet.next();
        Integer toId = rowSet.getInt("to_id");
        Integer fromId = rowSet.getInt("from_id");
        sql =
                "INSERT INTO user_friend (user_id, friend_id) " +
                "VALUES (?, ?); " +
                "INSERT INTO user_friend (user_id, friend_id) " +
                "VALUES (?, ?); ";
        jdbcTemplate.update(sql, toId, fromId, fromId, toId);
    }

    @Override
    public void rejectFriendRequest(Integer requestId) {
        String sql =
                "UPDATE friend_request " +
                "SET status = 'rejected' " +
                "WHERE request_id = ?;";
        jdbcTemplate.update(sql, requestId);
    }

    @Override
    public void cancelFriendRequest(Integer requestId) {
        String sql =
                "UPDATE friend_request " +
                "SET status = 'cancelled' " +
                "WHERE request_id = ?;";
        jdbcTemplate.update(sql, requestId);
    }

    @Override
    public FriendsPageDto getFriendPageByUserId(Integer userId) {
        FriendsPageDto page = new FriendsPageDto();
        page.setFriends(getFriendsById(userId));
        page.setRequests(getToRequestsByUserId(userId));
        page.setPending(getFromRequestsByUserId(userId));
        return page;
    }

    private List<FriendDto> friendListMapper(SqlRowSet rowSet) {
        List<FriendDto> friends = new ArrayList<>();
        while(rowSet.next()) {
            friends.add(friendMapper(rowSet));
        }
        return friends;
    }

    private FriendDto friendMapper(SqlRowSet rowSet) {
        try {
            FriendDto friend = new FriendDto();
            friend.setFriendId(rowSet.getInt("friend_id"));
            friend.setFriendName(userDao.getUserById(friend.getFriendId()).getUsername());
            return friend;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<FriendRequestDto> friendRequestListMapper(SqlRowSet rowSet) {
        List<FriendRequestDto> friends = new ArrayList<>();
        while (rowSet.next()) {
            friends.add(friendRequestMapper(rowSet));
        }
        return friends;
    }


    private FriendRequestDto friendRequestMapper(SqlRowSet rowSet) {
        try {
            FriendRequestDto request = new FriendRequestDto();
            request.setRequestId(rowSet.getInt("request_id"));
            request.setFromId(rowSet.getInt("from_id"));
            request.setFromName(userDao.getUserById(request.getFromId()).getUsername());
            request.setToId(rowSet.getInt("to_id"));
            request.setToName(userDao.getUserById(request.getToId()).getUsername());
            request.setStatus(rowSet.getString("status"));
            return request;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
