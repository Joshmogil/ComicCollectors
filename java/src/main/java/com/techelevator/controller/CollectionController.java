package com.techelevator.controller;

import com.techelevator.dao.CollectionDataDao;
import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.AddComicDTO;
import com.techelevator.model.Collection;
import com.techelevator.model.CreateCollectionDTO;
import com.techelevator.model.MarvelComic;
import com.techelevator.services.MarvelComicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CollectionController {

    private CollectionDataDao collectionDao;
    private ComicDataDao comicDao;

    public CollectionController(CollectionDataDao collectionDao, ComicDataDao comicDao) {
        this.collectionDao = collectionDao;
        this.comicDao = comicDao;
    }

    //Marvel Service Declaration
    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/comics/";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);
    //

    @RequestMapping(path = "collections/create", method = RequestMethod.POST)//make a user not found exception
    public boolean createCollection(@RequestBody CreateCollectionDTO createCollectionDTO) {

        boolean collectionCreated = collectionDao.createCollection(createCollectionDTO.getUser_id(),createCollectionDTO.getCollection_name());

        return collectionCreated;

    }

    @RequestMapping(path = "collections/addcomic", method = RequestMethod.POST)//make a user not found exception
    public boolean addComicToCollection(@RequestBody AddComicDTO addComicDTO) {
        /*Example json body
        {
            "collection_id":2,
            "comic_id": 27649
        }
        */
        boolean comicAddedToCollection = false;

        try {
            MarvelComic marvelComic = marvelComicService.getComic(addComicDTO.getComic_id());

            String useableImgUrl = marvelComic.getImg_url() + "/portrait_uncanny." + marvelComic.getExtension();

            Integer comicSerialForCollection = comicDao.addComicToComicTable(marvelComic.getMarvel_id(), marvelComic.getTitle(), useableImgUrl, marvelComic.getDescription());

            comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollection_id(), comicSerialForCollection);

        } catch (Exception thisNeedToBeLessGeneral) {
            System.out.println("Failed to add comic to collection");
        }

        return comicAddedToCollection;

    }

}
