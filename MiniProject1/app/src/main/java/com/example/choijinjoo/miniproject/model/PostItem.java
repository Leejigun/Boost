package com.example.choijinjoo.miniproject.model;

/**
 * Created by choijinjoo on 2017. 7. 4..
 */

public class PostItem extends TimelineItem{
    private Integer imgId;
    private String content;

    public PostItem(Integer viewType, Integer imgId, String content) {
        super(viewType);
        this.imgId = imgId;
        this.content = content;
    }

    public PostItem(Integer viewType,  String content) {
        super(viewType);
        this.content = content;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
