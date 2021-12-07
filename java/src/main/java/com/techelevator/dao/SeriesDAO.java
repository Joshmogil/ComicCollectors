package com.techelevator.dao;

import com.techelevator.model.Series;

public interface SeriesDAO {

    Series getSingleSeriesByID(long seriesId);
}
