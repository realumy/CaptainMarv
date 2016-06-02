package com.example.alainbansais.marvel;

import java.util.ArrayList;
import java.util.HashMap;

public class Character {
    private String id;
    private String name;
    private String description;
    private String pathImg;

    public Character() {
        super();
        this.id = "";
        this.name = "";
        this.description = "";
        this.pathImg = "";

    }

    public Character(String id, String name, String description, String pathImg) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.pathImg = pathImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }
}
