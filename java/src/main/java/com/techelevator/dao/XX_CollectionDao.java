package com.techelevator.dao;

import com.techelevator.model.Collection;

import java.util.List;

public interface XX_CollectionDao {

    Collection getSingleCollectionByCollectionId (long collectionId);

    List <Collection> getAllCollections (); //Get all collections from all users

    List <Collection> getAllCollectionsByUser (long userId);

    //Need to add this in a new jdbcCollectionComic class?
//

    boolean createCollection(String collection_name, long user_id ); //What do we need to pass in to create a collection? userid?



}
