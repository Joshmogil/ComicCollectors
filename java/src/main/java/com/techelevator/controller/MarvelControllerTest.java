package com.techelevator.controller;

import com.techelevator.model.MarvelComic;
import com.techelevator.services.MarvelComicService;
import net.minidev.json.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MarvelControllerTest {

    public MarvelControllerTest () {}

    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/comics?ts=";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);

    @RequestMapping(path = "marveltest/{comicId}", method = RequestMethod.GET)
    public MarvelComic testGetComicId(@PathVariable long comicId){

        MarvelComic comicFromMarvel = marvelComicService.getComic(comicId);

        return comicFromMarvel;
    }

    @RequestMapping(path = "marveljsontest/{comicId}", method = RequestMethod.GET)
    public JSONObject testGetComicJson(@PathVariable long comicId){

        JSONObject comicJsonFromMarvel = marvelComicService.getComicJsonObj(comicId);

        return comicJsonFromMarvel;
    }


}
