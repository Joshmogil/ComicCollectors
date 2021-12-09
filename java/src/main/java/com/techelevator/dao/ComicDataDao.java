package com.techelevator.dao;


import com.techelevator.model.Comic;

import java.util.List;

public interface ComicDataDao {

    String getComicDataByID(int id);


    Comic getComicById(int id);

    List<Comic> getAllComics();

}