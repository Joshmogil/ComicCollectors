package com.techelevator.model;

import java.util.ArrayList;

public class Comic {

    private long comicId;
    private String title;
    private String imgUrl;
    private ArrayList<Long> characterIds;
    private ArrayList<String> characterNames;
    private long teamId;
    private String teamName;
    private long seriesId;
    private String seriesName;

    public Comic(long comicId, String title, String imgUrl, ArrayList<Long> characterIds, ArrayList<String> characterNames,
                 int teamId, String teamName, int seriesId, String seriesName) {
        this.comicId = comicId;
        this.title = title;
        this.imgUrl = imgUrl;
        this.characterIds = characterIds;
        this.characterNames = characterNames;
        this.teamId = teamId;
        this.teamName = teamName;
        this.seriesId = seriesId;
        this.seriesName = seriesName;
    }

    public long getComicId() {
        return comicId;
    }

    public void setComicId(long comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ArrayList<Long> getCharacterIds() {
        return characterIds;
    }

    public void setCharacterIds(ArrayList<Long> characterIds) {
        this.characterIds = characterIds;
    }

    public ArrayList<String> getCharacterNames() {
        return characterNames;
    }

    public void setCharacterNames(ArrayList<String> characterNames) {
        this.characterNames = characterNames;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}
