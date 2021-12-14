package com.techelevator.model.StatisticModels;

public class CharacterWithStats {

    private long characterId;
    private String characterName;
    private String imgUrl;
    private String description;
    private long count;

    public CharacterWithStats() {
    }

    public CharacterWithStats(long characterId, String characterName, String imgUrl, String description, long count) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.imgUrl = imgUrl;
        this.description = description;
        this.count = count;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
