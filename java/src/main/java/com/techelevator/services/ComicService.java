package com.techelevator.services;

import com.techelevator.model.SimpleComicDto;

import java.util.List;

public interface ComicService {
    List<SimpleComicDto> getComicsByTitle(String title);
}
