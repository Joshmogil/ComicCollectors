package com.techelevator.dao;

import com.techelevator.model.CollectionComic;
import com.techelevator.model.Comic;
import org.springframework.context.annotation.Bean;


import java.util.List;


public interface CollectionComicDao {

    List <Comic> getAllComicsInCollectionByCollectionId(long collectionId);
}
