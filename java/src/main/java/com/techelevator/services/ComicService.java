package com.techelevator.services;

import com.google.gson.JsonArray;
import com.techelevator.model.ComicDto;
import com.techelevator.model.SimpleComicDto;

import java.util.List;

public interface ComicService {
    List<SimpleComicDto> getComicsByTitle(String title);
    List<ComicDto> getComicsDtoByTitle(String title);

    List<ComicDto> getComicsDto();
}
