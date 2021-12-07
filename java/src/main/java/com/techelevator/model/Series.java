package com.techelevator.model;

public class Series {

    private long seriesId;
    private String seriesTitle;

    public Series() {}

    public Series(long seriesId, String seriesTitle) {
        this.seriesId = seriesId;
        this.seriesTitle = seriesTitle;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    @Override
    public String toString() {
        return "Series{" +
                "seriesId=" + seriesId +
                ", seriesTitle='" + seriesTitle + '\'' +
                '}';
    }
}
