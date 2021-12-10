package com.techelevator.dao;

import com.techelevator.model.Series;

public interface SeriesDataDao {

    Series getSingleSeriesById(long seriesId);
}
