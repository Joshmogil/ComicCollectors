package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;

import java.util.List;

public interface CollectionComicDataDao {

    List<Comic> getAllComicsInCollectionByCollectionId(Long collectionId);
}
