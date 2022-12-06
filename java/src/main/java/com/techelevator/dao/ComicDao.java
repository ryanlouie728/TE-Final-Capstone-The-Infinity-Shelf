package com.techelevator.dao;

import com.techelevator.model.SimpleComicDto;

import java.util.List;

public interface ComicDao {

    List<SimpleComicDto> listSimple();

    List<SimpleComicDto> listSimpleByCollection(int collectionId);

    void createComic(SimpleComicDto comic);

    void createComicList(List<SimpleComicDto> comics);

    List<SimpleComicDto> listSimpleByTitle(String title);

}
