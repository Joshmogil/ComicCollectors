package com.techelevator.dao;

import com.techelevator.model.Collection;

import java.util.List;

public interface CollectionDao {

    Collection getSingleCollection (long collectionId);

    List <Collection> getAllCollections (); //Get all collections from all users

    List <Collection> getAllCollectionsByUser (long userId);

    void addComicToCollection (long userId, long comicId);

}
