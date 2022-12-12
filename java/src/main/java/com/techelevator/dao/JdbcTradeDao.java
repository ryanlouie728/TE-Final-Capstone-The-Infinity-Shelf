package com.techelevator.dao;

import com.techelevator.model.ComicDto;
import com.techelevator.model.trade.TradeComicDto;
import com.techelevator.model.trade.TradeDto;
import com.techelevator.model.trade.TradeUserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTradeDao implements TradeDao {

    private final JdbcTemplate jdbcTemplate;
    private final ComicDao comicDao;
    private final UserDao userDao;
    private final CollectionDao collectionDao;

    public JdbcTradeDao(JdbcTemplate jdbcTemplate, ComicDao comicDao, UserDao userDao, CollectionDao collectionDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.comicDao = comicDao;
        this.userDao = userDao;
        this.collectionDao = collectionDao;
    }

    @Override
    public List<TradeDto> getTradesByUserId(Integer userId) {
        String sql =
                "SELECT trade.trade_id, trade.status " +
                "FROM trade " +
                "JOIN trade_user ON trade.trade_id = trade_user.trade_id " +
                "WHERE trade_user.user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return tradeListMapper(rowSet);
    }

    @Override
    public List<TradeDto> getPendingByUserId(Integer userId) {
        String sql =
                "SELECT trade.trade_id, trade.status " +
                "FROM trade " +
                "JOIN trade_user ON trade.trade_id = trade_user.trade_id " +
                "WHERE trade_user.user_id = ? " +
                "AND trade_user.role = 'sender' " +
                "AND trade.status = 'pending' ;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        return tradeListMapper(rowSet);
    }

    @Override
    public List<TradeDto> getRequestsByUserId(Integer userId) {
        String sql =
                "SELECT trade.trade_id, trade.status " +
                "FROM trade " +
                "JOIN trade_user ON trade.trade_id = trade_user.trade_id " +
                "WHERE trade_user.user_id = ? " +
                "AND trade_user.role = 'recipient' " +
                "AND trade.status = 'pending' ;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return tradeListMapper(rowSet);
    }

    @Override
    public Boolean acceptTrade(Integer tradeId) {
        try {
            String sql =
                    "UPDATE trade " +
                    "SET status = 'accepted' " +
                    "WHERE trade_id = ?;";
            jdbcTemplate.update(sql, tradeId);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean rejectTrade(Integer tradeId) {
        try {
            String sql =
                    "UPDATE trade " +
                    "SET status = 'rejected' " +
                    "WHERE trade_id = ?;";
            jdbcTemplate.update(sql, tradeId);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean transferComic(TradeComicDto comic, Integer newBaseId) {
        try {
            String sql =
                    "DELETE " +
                    "FROM collection_comic " +
                    "WHERE comic_id = ? " +
                    "AND coll_id = ?; " +
                    "INSERT INTO collection_comic (coll_id, comic_id) " +
                    "VALUES (?, ?);";
            jdbcTemplate.update(sql, comic.getComicDto().getId(), comic.getCollectionId(), newBaseId, comic.getComicDto().getId());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public TradeDto getTradeById(Integer tradeId) {
        String sql =
                "SELECT trade_id, status " +
                "FROM trade " +
                "WHERE trade_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tradeId);
        if (rowSet.next()) {
            return tradeMapper(rowSet);
        }
        return null;
    }

    @Override
    public Boolean tradeExists(Integer tradeId) {
        String sql =
                "SELECT trade_id " +
                "FROM trade " +
                "WHERE trade_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tradeId);
        return rowSet.next();
    }

    @Override
    public Boolean tradeIsPending(Integer tradeId) {
        String sql =
                "SELECT status " +
                "FROM trade " +
                "WHERE trade_id = ?;";
        String status = jdbcTemplate.queryForObject(sql, String.class, tradeId);
        return status.equals("pending");
    }

    private Integer createTradeRecord() {
        String sql =
                "INSERT INTO trade (status) " +
                "VALUES ('pending') " +
                "RETURNING trade_id;";
        Integer tradeId = jdbcTemplate.queryForObject(sql, Integer.class);
        return tradeId;
    }

    private Boolean createTradeUsers(TradeDto trade) {
        try {
            TradeUserDto sender = new TradeUserDto();
            TradeUserDto recipient = new TradeUserDto();
            for (TradeUserDto user : trade.getUsers()) {
                if (user.getRole().equals("sender")) {
                    sender = user;
                } else {
                    recipient = user;
                }
            }

            String sql =
                    "INSERT INTO trade_user (trade_id, user_id, role) " +
                    "VALUES (?, ?, ?); " +
                    "INSERT INTO trade_user (trade_id, user_id, role) " +
                    "VALUES (?, ?, ?); ";
            jdbcTemplate.update(sql, trade.getTradeId(), trade.getUsers().get(0).getUserDto().getId(), trade.getUsers().get(0).getRole(), trade.getTradeId(), trade.getUsers().get(1).getUserDto().getId(), trade.getUsers().get(1).getRole());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private Boolean createTradeComics(TradeDto trade) {
        try {
            for (TradeComicDto comic : trade.getComics()) {
                String sql =
                        "INSERT INTO trade_comic (trade_id, from_id, to_id, comic_id, coll_id) " +
                        "VALUES (?, ?, ?, ?, ?);";
                jdbcTemplate.update(sql, trade.getTradeId(), comic.getFrom().getId(), comic.getTo().getId(), comic.getComicDto().getId(), comic.getCollectionId());
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public Boolean createTrade(TradeDto trade) {
        for (TradeComicDto comic : trade.getComics()) {
            if (!collectionDao.collectionHasComic(comic.getCollectionId(), comic.getComicDto().getId())) {
                return false;
            }
        }
        Integer id = createTradeRecord();
        if (id == null) {
            return false;
        }
        trade.setTradeId(id);
        if (!createTradeUsers(trade)) {
            return false;
        }
        return createTradeComics(trade);
    }

    private List<TradeComicDto> getComicsByTradeId(Integer tradeId) {
        String sql =
                "SELECT trade_id, comic_id, from_id, to_id, coll_id " +
                "FROM trade_comic " +
                "WHERE trade_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tradeId);
        return tradeComicListMapper(rowSet);
    }

    private List<TradeUserDto> getUsersByTradeId(Integer tradeId) {
        String sql =
                "SELECT user_id, role " +
                "FROM trade_user " +
                "WHERE trade_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tradeId);
        return tradeUserListMapper(rowSet);
    }


    private List<TradeDto> tradeListMapper(SqlRowSet rowSet) {
        List<TradeDto> trades = new ArrayList<>();
        while (rowSet.next()) {
            trades.add(tradeMapper(rowSet));
        }
        return trades;
    }

    private TradeDto tradeMapper(SqlRowSet rowSet) {
        try {
            TradeDto trade = new TradeDto();
            trade.setTradeId(rowSet.getInt("trade_id"));
            trade.setStatus(rowSet.getString("status"));
            trade.setComics(getComicsByTradeId(trade.getTradeId()));
            trade.setUsers(getUsersByTradeId(trade.getTradeId()));
            return trade;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<TradeComicDto> tradeComicListMapper(SqlRowSet rowSet) {
        List<TradeComicDto> comics = new ArrayList<>();
        while (rowSet.next()) {
            comics.add(tradeComicMapper(rowSet));
        }
        return comics;
    }

    private TradeComicDto tradeComicMapper(SqlRowSet rowSet) {
        try {
            TradeComicDto comic = new TradeComicDto();
            comic.setComicDto(comicDao.getComicById(rowSet.getInt("comic_id")));
            comic.setFrom(userDao.getUserDtoById(rowSet.getInt("from_id")));
            comic.setTo(userDao.getUserDtoById(rowSet.getInt("to_id")));
            comic.setCollectionId(rowSet.getInt("coll_id"));
            return comic;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<TradeUserDto> tradeUserListMapper(SqlRowSet rowSet) {
        List<TradeUserDto> users = new ArrayList<>();
        while (rowSet.next()) {
            users.add(tradeUserMapper(rowSet));
        }
        return users;
    }

    private TradeUserDto tradeUserMapper(SqlRowSet rowSet) {
        try {
            TradeUserDto user = new TradeUserDto();
            user.setUserDto(userDao.getUserDtoById(rowSet.getInt("user_id")));
            user.setRole(rowSet.getString("role"));
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
