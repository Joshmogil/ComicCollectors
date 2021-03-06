package com.techelevator.controller;

import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.MarvelCharacter;
import com.techelevator.model.MarvelComic;
import com.techelevator.services.MarvelComicService;
import net.minidev.json.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MarvelControllerTest {

    private ComicDataDao comicDao;

    public MarvelControllerTest () {}

    public MarvelControllerTest(ComicDataDao comicDao) {
        this.comicDao = comicDao;
    }

    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);





    @RequestMapping(path = "marvelcomic/{comicId}", method = RequestMethod.GET)
    public MarvelComic testGetComic(@PathVariable long comicId){

        MarvelComic marvelComic = marvelComicService.getComic(comicId);

        return marvelComic;
    }

//    @RequestMapping(path = "marvelcomics/{characterName}", method = RequestMethod.GET)
//    public List<MarvelComic> testGetComicsCharacterName(@PathVariable String characterName){
//
//        List<MarvelComic> comicListWithComicIds = new ArrayList<>();
//
//        List<MarvelComic> comicList = marvelComicService.getComicListByCharacterName(characterName);
//
//        for(MarvelComic marvelComic:comicList){
//
//            System.out.println(marvelComic.getMarvelId());
//
//            Integer comicId = comicDao.getComicSerialByMarvelId(marvelComic.getMarvelId());
//            System.out.println(comicId);
//
//
//        }
//
//
//
////        for(MarvelComic marvelComic:comicList){
////            //set comic id
////
////            if(comicDao.getComicSerialByMarvelId(marvelComic.getMarvelId()) == -1){
////
////                Integer comicSerialForCollection = comicDao.addComicToComicTable(marvelComic.getMarvelId(), marvelComic.getTitle(), marvelComic.getImgUrl(), marvelComic.getDescription());
////
////                marvelComic.setComicId(Long.valueOf(comicSerialForCollection));
////
////            }else{
////
////                marvelComic.setComicId(Long.valueOf(comicDao.getComicSerialByMarvelId(marvelComic.getMarvelId())));
////
////            }
////
////            comicListWithComicIds.add(marvelComic);
////
////        }
////
////        return comicListWithComicIds;
//
//        return comicList;
//    }

    @RequestMapping(path = "characters/{marvelComicId}", method = RequestMethod.GET)
    public List<MarvelCharacter> testGetCharactersComic(@PathVariable long marvelComicId){

        List<MarvelCharacter> characterList = marvelComicService.getCharacterListByComicId(marvelComicId);

        return characterList;
    }

    @RequestMapping(path = "character/{characterName}", method = RequestMethod.GET)
    public long testGetCharacterId(@PathVariable String characterName){

        long id = marvelComicService.getCharacterIdByName(characterName);

        return id;
    }


    @RequestMapping(path = "marveltest/{comicId}", method = RequestMethod.GET)
    public MarvelComic testGetComicId(@PathVariable long comicId){

        List<String> importantComicInfo = marvelComicService.getComicInfo(comicId);

        MarvelComic marvelComic = new MarvelComic();

        marvelComic.setMarvelId(Long.valueOf(importantComicInfo.get(0)));
        marvelComic.setTitle(importantComicInfo.get(1));
        marvelComic.setDescription(importantComicInfo.get(2));
        marvelComic.setImgUrl(importantComicInfo.get(3));
        marvelComic.setExtension(importantComicInfo.get(4));

        return marvelComic;
    }

    @RequestMapping(path = "marvelstringtest/{comicId}", method = RequestMethod.GET)
    public String testGetComicJsonAsString(@PathVariable long comicId){

        String comicString = marvelComicService.getComicString(comicId);

        return comicString;
    }


    @RequestMapping(path = "marveljsontest/{comicId}", method = RequestMethod.GET)
    public JSONObject testGetComicJson(@PathVariable long comicId){

        JSONObject comicJsonFromMarvel = marvelComicService.getComicJsonObj(comicId);

        return comicJsonFromMarvel;
    }
//
//    @RequestMapping(path = "marvelcomicdatatest/{comicId}", method = RequestMethod.GET)
//    public MarvelComicData testGetComicData(@PathVariable long comicId){
//
//        MarvelComicData comicData = marvelComicService.getComicData(comicId);
//
//        return comicData;
//    }


}
