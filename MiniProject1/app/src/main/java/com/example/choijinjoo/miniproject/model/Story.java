package com.example.choijinjoo.miniproject.model;

/**
 * Created by choijinjoo on 2017. 7. 4..
 */

public class Story {
    private int viewType;
    private String name;

    public Story(int viewType, String name) {
        this.viewType = viewType;
        this.name = name;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
