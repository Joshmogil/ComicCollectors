package com.techelevator.dao;


import com.techelevator.model.Comic;
import com.techelevator.model.MarvelComic;
import com.techelevator.model.StatisticModels.ComicWithStats;

import java.util.List;

public interface ComicDataDao {

    Long getMarvelComicIdByOurComicId(int comic_id);

    List<ComicWithStats> getComicsWithAppearances();

    List<Long> getAllMarvelIdsOfComics();

    Integer getComicSerialByMarvelId(long marvelId);

    Integer addComicToComicTable(Long marvelId,String comicTitle,String imgUrl, String description);

    Comic getComicById(int id);

    List<Comic> getAllComics();

}