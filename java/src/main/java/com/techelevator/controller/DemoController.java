package com.techelevator.controller;

import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.Comic;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
class DemoController {

    //private ComicDao comicDao;
    private ComicDataDao cdd;

    public DemoController (ComicDataDao cdd){
        //this.comicDao = comicDao;
        this.cdd = cdd;

    }

    @RequestMapping(path = "comics/greet", method = RequestMethod.GET)
    public String Test(){

        return "YOU GET A COMIC!";

    }

    @RequestMapping(path = "comics/data/{comicId}", method = RequestMethod.GET)
    public String Test(@PathVariable int comicId){

       return cdd.getComicDataByID(comicId);

    }

    @RequestMapping(path = "comics/newdata/{comicId}", method = RequestMethod.GET)
    public Comic getComicById(@PathVariable int comicId) {return cdd.getComicById(comicId);}

}
