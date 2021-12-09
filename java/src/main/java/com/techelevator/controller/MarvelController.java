package com.techelevator.controller;

import com.techelevator.dao.CollectionComicDao;
import com.techelevator.dao.XX_ComicDao;
import com.techelevator.model.MarvelComic;
import com.techelevator.services.MarvelComicService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
public class MarvelController {
    private CollectionComicDao collectionComicDao;
    private XX_ComicDao comicDao;

    public MarvelController() {}

    public MarvelController (CollectionComicDao collectionComicDao ){this.collectionComicDao = collectionComicDao;}

    String API_BASE_URL = "http://gateway.marvel.com/v1/public/comics?ts=";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    @RequestMapping(path = "test/{comicId}", method = RequestMethod.GET)//make a transfer not found exception
    public MarvelComic testGetComicId(@PathVariable long comicId){

        MarvelComic comicFromMarvel = null;

        return comicFromMarvel;
    }

    MarvelComicService marvelComicService = new MarvelComicService(API_BASE_URL, privateKey,publicKey);
}
