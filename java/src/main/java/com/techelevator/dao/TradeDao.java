package com.techelevator.dao;

import com.techelevator.model.trade.TradeComicDto;
import com.techelevator.model.trade.TradeDto;

import java.util.List;

public interface TradeDao {
    List<TradeDto> getTradesByUserId(Integer userId);
    List<TradeDto> getPendingByUserId(Integer userId);
    List<TradeDto> getRequestsByUserId(Integer userId);

    Boolean createTrade(TradeDto trade);

    Boolean tradeExists(Integer tradeId);

    Boolean acceptTrade(Integer tradeId);

    Boolean rejectTrade(Integer tradeId);

    Boolean cancelTrade(Integer tradeId);

    TradeDto getTradeById(Integer tradeId);

    Boolean transferComic(TradeComicDto comic, Integer newBaseId);

    Boolean tradeIsPending(Integer tradeId);
}
