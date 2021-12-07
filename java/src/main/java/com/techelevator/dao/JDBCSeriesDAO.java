package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Series;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSeriesDAO implements SeriesDAO{

    private JdbcTemplate jdbcTemplate;

    public JDBCSeriesDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Series getSingleSeriesByID(long series_id) {

        Series series = null;

        String sql = "SELECT * " +
                     "FROM series " +
                     "WHERE series_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, series_id);
        if (results.next()) {
            series = mapRowToSeries(results);
        }
        return series;
    }

    private Series mapRowToSeries(SqlRowSet rowSet) {
        Series series = new Series();

        series.setSeriesId(rowSet.getLong("series_id"));
        series.setSeriesTitle(rowSet.getString("series_title"));

        return series;
    }

}
