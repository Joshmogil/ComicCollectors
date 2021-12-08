package com.techelevator.dao;

import com.techelevator.model.Series;

public interface TempSeriesDao {
    Series getSingleSeriesByID(long seriesId);
}
