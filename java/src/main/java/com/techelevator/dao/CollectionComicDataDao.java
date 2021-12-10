package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;

import java.util.List;

public interface CollectionComicDataDao {

    //Add Comic To Collection
//    boolean addComicToCollection(long userId, long comicId, long collectionId);

    List<Comic> getAllComicsInCollectionByCollectionId(Long collectionId);
}
