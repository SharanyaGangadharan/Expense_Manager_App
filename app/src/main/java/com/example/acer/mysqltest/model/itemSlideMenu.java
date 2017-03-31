package com.example.acer.mysqltest.model;

/**
 * Created by acer on 5/8/2016.
 */
public class itemSlideMenu {

    private  int imgId;
    private String title;

    public itemSlideMenu(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
