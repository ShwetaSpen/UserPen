package com.example.testonenew.model;

public class itemOwnerModel {

    int image;
    String name;
    String Service;

    public itemOwnerModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public void changeText(String text)
    {
        name =text;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }
}
