package com.techelevator.controller;

import com.techelevator.dao.CollectionComicDao;
import com.techelevator.model.CollectionComic;
import com.techelevator.model.Comic;
import com.techelevator.model.MarvelComic;
import com.techelevator.services.MarvelComicService;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController


public class ComicController {

    private CollectionComicDao collectionComicDao;

    public ComicController() {}

    public ComicController (CollectionComicDao collectionComicDao ){this.collectionComicDao = collectionComicDao;}

    String API_BASE_URL = "http://gateway.marvel.com/v1/public/comics?ts=";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(API_BASE_URL, privateKey,publicKey);

    /*@RequestMapping(path = "comic", method = RequestMethod.POST)
    public Boolean createTransfer(@RequestBody CollectionComic collectionComic) {

        MarvelComic comicFromMarvel = marvelComicService.getComic(collectionComic.getComic_id());

        return true;

    }*/

    @RequestMapping(path = "test/{comicId}", method = RequestMethod.GET)//make a transfer not found exception
    public MarvelComic testGetComicId(@PathVariable long comicId){

        MarvelComic comicFromMarvel = marvelComicService.getComic(comicId);

        return comicFromMarvel;
    }

    @RequestMapping(path = "collections/{collectionId}", method = RequestMethod.GET)
    public List<Comic> getAllComicsInCollectionByCollectionId(@PathVariable long collectionId) {

        List<Comic> comics = collectionComicDao.getAllComicsInCollectionByCollectionId(collectionId);
        return comics;
    }




}
