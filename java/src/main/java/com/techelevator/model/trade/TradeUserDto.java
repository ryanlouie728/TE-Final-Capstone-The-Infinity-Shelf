package com.techelevator.model.trade;

import com.techelevator.model.UserDto;

public class TradeUserDto {
    private UserDto userDto;
    private String role;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
