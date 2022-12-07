package com.techelevator.controller;


import com.techelevator.dao.ComicDao;
import com.techelevator.model.ComicDto;
import com.techelevator.model.SimpleComicDto;
import com.techelevator.services.ComicService;
import com.techelevator.services.RestComicService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comics")
public class ComicController {
    private ComicDao comicDao;
    private ComicService comicService = new RestComicService();

    public ComicController(ComicDao comicDao) {
        this.comicDao = comicDao;
    }

    @GetMapping("/simple")
    public List<SimpleComicDto> list(@RequestParam(defaultValue = "", name = "title") String title) {
        return comicDao.listSimple();
//        if (title.isBlank()) {
//            return comicDao.listSimple();
//        } else {
//            comicDao.createComicList(comicService.getComicsByTitle(title));
//            return comicDao.listSimpleByTitle(title);
//        }
    }

    @GetMapping("/simple/{collectionId}")
    public List<SimpleComicDto> listSimpleByCollectionId(@PathVariable Integer collectionId) {
        return comicDao.listSimpleByCollection(collectionId);
    }

    @PostMapping("")
    public void addComic(@Valid @RequestBody SimpleComicDto comic) {
        comicDao.createComic(comic);
    }

    @GetMapping("")
    public List<ComicDto> listComicsByTitle(@RequestParam(defaultValue = "") String title) {
        List<ComicDto> comics;
        if (title.isBlank()) {
            comics = comicService.getComicsDto();
        } else {
            comics = comicService.getComicsDtoByTitle(title);
        }
        comicDao.createComicList(comics);
        return comics;
    }

    @GetMapping("/{comicId}")
    public ComicDto getComicById(@PathVariable Integer comicId) {
        return comicDao.getComicById(comicId);
    }

}
