package com.techelevator.model;

public class Character {

    private long characterId;
    private String characterName;

    public Character(){}

    public Character(long characterId, String characterName) {
        this.characterId = characterId;
        this.characterName = characterName;
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

    @Override
    public String toString() {
        return "Character{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                '}';
    }
}
