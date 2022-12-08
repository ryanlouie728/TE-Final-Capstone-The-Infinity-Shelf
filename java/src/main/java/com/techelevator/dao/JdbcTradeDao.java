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

    public JdbcTradeDao(JdbcTemplate jdbcTemplate, ComicDao comicDao, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.comicDao = comicDao;
        this.userDao = userDao;
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
    public Boolean createTrade(TradeDto trade) {
        for (TradeComicDto comic : trade.getComics()) {
            if (!comicDao.userHasComic(comic.getFrom().getId(), comic.getComicDto().getId())) {
                return false;
            }
        }


        return null;
    }

    private List<TradeComicDto> getComicsByTradeId(Integer tradeId) {
        String sql =
                "SELECT trade_id, comic_id, from_id, to_id " +
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
