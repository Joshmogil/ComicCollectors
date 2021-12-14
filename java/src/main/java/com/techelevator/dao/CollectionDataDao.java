package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;

import java.security.Principal;
import java.util.List;

public interface CollectionDataDao {

    boolean addComicToCollectionComic(Integer collectionId, Integer comicIdSerial);

    long createCollection(long userId, String collectionName);

    Collection getCollectionById(long collectionId);

    List<Collection> getAllCollections();

    List <Collection> getUserCollections(long userId);

//    Collection createCollection(Collection newCollection, Principal principal);
}
