package com.techelevator.model.trade;

import com.techelevator.model.ComicDto;

import java.util.List;

public class TradeDto {
    private Integer tradeId;
    private List<TradeComicDto> comics;
    private List<TradeUserDto> users;
    private String status;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public List<TradeComicDto> getComics() {
        return comics;
    }

    public void setComics(List<TradeComicDto> comics) {
        this.comics = comics;
    }

    public List<TradeUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<TradeUserDto> users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
