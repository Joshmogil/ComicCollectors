package com.techelevator.model;

public class Comic {

    private long comicId;
    private String comicName;
    private long characterId;
    private String characterName;
    private long teamId;
    private String teamName;
    private long seriesId;
    private String seriesName;

    public Comic(long comicId, String comicName, long characterId, String characterName, long teamId, String teamName, long seriesId, String seriesName) {
        this.comicId = comicId;
        this.comicName = comicName;
        this.characterId = characterId;
        this.characterName = characterName;
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

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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
