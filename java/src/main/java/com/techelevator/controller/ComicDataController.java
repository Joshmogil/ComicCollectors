package com.techelevator.controller;

import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.Comic;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ComicDataController {

    private ComicDataDao cdd;

    

    public ComicDataController (ComicDataDao cdd) {
        this.cdd = cdd;
    }

    @RequestMapping(path = "comics/{comicId}", method = RequestMethod.GET)
    public Comic getComicById(@PathVariable int comicId) {return cdd.getComicById(comicId);}

    @RequestMapping(path = "comics", method = RequestMethod.GET)
    public List<Comic> getAllComics() {return cdd.getAllComics();}
}
