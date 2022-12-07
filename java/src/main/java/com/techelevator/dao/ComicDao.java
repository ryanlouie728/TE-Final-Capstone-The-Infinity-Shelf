package com.techelevator.dao;

import com.techelevator.model.ComicDto;
import com.techelevator.model.SimpleComicDto;

import java.util.List;

public interface ComicDao {

    List<SimpleComicDto> listSimple();

    List<SimpleComicDto> listSimpleByCollection(int collectionId);

    void createComic(SimpleComicDto comic);

    void createComicList(List<ComicDto> comics);

    ComicDto getComicById(Integer comicId);

    List<SimpleComicDto> listSimpleByTitle(String title);

    List<ComicDto> listComicsByCollectionId(Integer collectionId);

    void createComic(ComicDto comic);
}
