package com.techelevator.dao;

import com.techelevator.model.Comic;

import java.util.List;

public interface ComicDao {

    Comic getSingleComicById(long comicId);

    List<Comic> getAllComics();


}
