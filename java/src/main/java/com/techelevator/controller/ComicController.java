package com.techelevator.controller;


import com.techelevator.dao.ComicDao;
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

    public ComicController(ComicDao comicDao) {
        this.comicDao = comicDao;
    }

    @GetMapping("/simple")
    public List<SimpleComicDto> list() {
        return comicDao.listSimple();
    }

    @GetMapping("/simple/{collectionId}")
    public List<SimpleComicDto> listSimpleByCollectionId(@PathVariable Integer collectionId) {
        return comicDao.listSimpleByCollection(collectionId);
    }

    @PostMapping("")
    public void addComic(@Valid @RequestBody SimpleComicDto comic) {
        comicDao.createComic(comic);
    }

    @GetMapping("/api/{title}")
    public List<SimpleComicDto> listComicsByTitle(@PathVariable String title) {
        return new RestComicService().getComicsByTitle(title);
    }

}
