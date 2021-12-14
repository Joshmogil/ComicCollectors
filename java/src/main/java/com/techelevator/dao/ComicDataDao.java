package com.techelevator.dao;


import com.techelevator.model.Comic;
import com.techelevator.model.MarvelComic;

import java.util.List;

public interface ComicDataDao {

    List<Long> getAllMarvelIdsOfComics();

    Integer getComicSerialByMarvelId(long marvelId);

    Integer addComicToComicTable(Long marvelId,String comicTitle,String imgUrl, String description);

    String getComicDataByID(int id);

    Comic getComicById(int id);

    List<Comic> getAllComics();

}