package com.techelevator.model.trade;

import com.techelevator.model.ComicDto;
import com.techelevator.model.UserDto;
import com.techelevator.model.login.User;

public class TradeComicDto {
    private ComicDto comicDto;
    private UserDto from;
    private UserDto to;

    public ComicDto getComicDto() {
        return comicDto;
    }

    public void setComicDto(ComicDto comicDto) {
        this.comicDto = comicDto;
    }

    public UserDto getFrom() {
        return from;
    }

    public void setFrom(UserDto from) {
        this.from = from;
    }

    public UserDto getTo() {
        return to;
    }

    public void setTo(UserDto to) {
        this.to = to;
    }
}
