package com.loyality.testproject.navigation.model;

/**
 * Created by Ravi on 29/07/15.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private int imageId;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, int imageId) {
        this.showNotify = showNotify;
        this.title = title;
        this.imageId = imageId;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {return imageId;}

    public void setImageId (int imageId)
    {
        this.imageId = imageId;
    }
}
