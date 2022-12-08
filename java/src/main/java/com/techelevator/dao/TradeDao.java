package com.techelevator.dao;

import com.techelevator.model.trade.TradeComicDto;

import java.util.List;

public interface TradeDao {
    List<TradeComicDto> getTradesByUserId(Integer userId);

}
