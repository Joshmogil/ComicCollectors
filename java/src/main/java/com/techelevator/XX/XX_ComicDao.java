package com.techelevator.XX;

import com.techelevator.model.Comic;

import java.util.List;

public interface XX_ComicDao {

    Comic getSingleComicById(long comicId);

    List<Comic> getAllComics();


}
