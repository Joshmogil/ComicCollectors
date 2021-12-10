package com.techelevator.dao;

import com.techelevator.model.Collection;

import java.security.Principal;
import java.util.List;

public interface CollectionDataDao {

    boolean createCollection(long userId, String collectionName);

    Collection getCollectionById(long collectionId);

    List<Collection> getAllCollections();

    List <Collection> getUserCollections(long userId);

//    Collection createCollection(Collection newCollection, Principal principal);
}
