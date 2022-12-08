package com.techelevator.dao;

import com.techelevator.model.trade.TradeDto;

import java.util.List;

public interface TradeDao {
    List<TradeDto> getTradesByUserId(Integer userId);
    Boolean createTrade(TradeDto trade);
}
