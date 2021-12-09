package com.techelevator.controller;

import com.techelevator.marvelmodel.MarvelComicData;
import com.techelevator.model.MarvelComic;
import com.techelevator.services.MarvelComicService;
import net.minidev.json.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MarvelControllerTest {

    public MarvelControllerTest () {}

    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/comics/";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);

    @RequestMapping(path = "marveltest/{comicId}", method = RequestMethod.GET)
    public MarvelComic testGetComicId(@PathVariable long comicId){

        List<String> importantComicInfo = marvelComicService.getComicInfo(comicId);

        MarvelComic marvelComic = new MarvelComic();

        marvelComic.setMarvel_id(Long.valueOf(importantComicInfo.get(0)));
        marvelComic.setTitle(importantComicInfo.get(1));
        marvelComic.setImg_url(importantComicInfo.get(2));
        marvelComic.setExtension(importantComicInfo.get(3));

        return marvelComic;
    }

    @RequestMapping(path = "marvelstringtest/{comicId}", method = RequestMethod.GET)
    public String testGetComicJsonAsString(@PathVariable long comicId){

        String comicString = marvelComicService.getComicString(comicId);

        return comicString;
    }
//
//
//    @RequestMapping(path = "marveljsontest/{comicId}", method = RequestMethod.GET)
//    public JSONObject testGetComicJson(@PathVariable long comicId){
//
//        JSONObject comicJsonFromMarvel = marvelComicService.getComicJsonObj(comicId);
//
//        return comicJsonFromMarvel;
//    }
//
//    @RequestMapping(path = "marvelcomicdatatest/{comicId}", method = RequestMethod.GET)
//    public MarvelComicData testGetComicData(@PathVariable long comicId){
//
//        MarvelComicData comicData = marvelComicService.getComicData(comicId);
//
//        return comicData;
//    }


}
