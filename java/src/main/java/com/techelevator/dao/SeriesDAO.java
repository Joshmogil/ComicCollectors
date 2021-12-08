package com.techelevator.dao;

import com.techelevator.model.Series;

public interface SeriesDao {

    Series getSingleSeriesByID(long seriesId);
}
