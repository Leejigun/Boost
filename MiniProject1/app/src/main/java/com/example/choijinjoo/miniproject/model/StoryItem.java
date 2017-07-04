package com.example.choijinjoo.miniproject.model;

import java.util.List;

/**
 * Created by choijinjoo on 2017. 7. 4..
 */

public class StoryItem extends TimelineItem{
    private List<Story> stories;

    public StoryItem(Integer viewType, List<Story> stories) {
        super(viewType);
        this.stories = stories;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
}
