package com.example.textrpggame;

import java.util.Arrays;

public class Location {
    private String imgUrl;
    private String description;
    private String shortInfo;
    private Location[] locations = {};

    Location(String description, String shortInfo,String imgUrl) {
        this.description = description;
        this.shortInfo = shortInfo;
        this.imgUrl=imgUrl;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public String getInfo() {
        return description;
    }

    public String[] getLocationsNames(){
        String[] locationsNames =new String[locations.length];
        for (int i=0;i< locations.length;i++){
            locationsNames[i]=locations[i].shortInfo;
        }
        return locationsNames;
    }

    public void addLocation(Location location) {
        locations = Arrays.copyOf(locations, locations.length + 1);
        locations[locations.length - 1] = location;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public Location goToLocation(int index) {
        if (index > 0 && index <= locations.length) {
            return locations[index - 1];
        } else {
            throw new IndexOutOfBoundsException("Nie mogę przejść do lokacji o nr:" + index);
        }
    }

    public boolean canGoToNewLocation() {
        return locations.length > 0;
    }

}
