package com.codecool.web.model;

public class Territory {

    String id;
    String description;
    int regionId;
    String regionDescription;

    public Territory(String id, String description, int regionId, String regionDescription) {
        this.id = id;
        this.description = description;
        this.regionId = regionId;
        this.regionDescription = regionDescription;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }
}
