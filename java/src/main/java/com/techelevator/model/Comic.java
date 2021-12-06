package com.techelevator.model;

import java.util.ArrayList;

public class Comic {

    private long comicId;
    private String title;
    private String imgUrl;
    private ArrayList<Integer> characterIds;
    private ArrayList<String> characterNames;
    private int teamId;
    private String teamName;
    private int seriesId;
    private String seriesName;

    public Comic(long comicId, String title, String imgUrl, ArrayList<Integer> characterIds, ArrayList<String> characterNames,
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

    public ArrayList<Integer> getCharacterIds() {
        return characterIds;
    }

    public void setCharacterIds(ArrayList<Integer> characterIds) {
        this.characterIds = characterIds;
    }

    public ArrayList<String> getCharacterNames() {
        return characterNames;
    }

    public void setCharacterNames(ArrayList<String> characterNames) {
        this.characterNames = characterNames;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}
