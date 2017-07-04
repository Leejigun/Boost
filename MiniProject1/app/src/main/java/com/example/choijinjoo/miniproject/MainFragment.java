package com.example.choijinjoo.miniproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.choijinjoo.miniproject.model.InduceUpdateItem;
import com.example.choijinjoo.miniproject.model.PostItem;
import com.example.choijinjoo.miniproject.model.Story;
import com.example.choijinjoo.miniproject.model.StoryItem;
import com.example.choijinjoo.miniproject.model.TimelineItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by choijinjoo on 2017. 7. 3..
 */

public class MainFragment extends Fragment {
    @BindView(R.id.recvTimeline)
    RecyclerView recvTimeline;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recvTimeline.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recvTimeline.setAdapter(new TimelineAdapter(makeTimelineMockData(), getActivity(),
                new TimelineAdapter.ItemClickedListener() {
                    @Override
                    public void Clicked(String msg) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                }));


    }

    private List<TimelineItem> makeTimelineMockData() {
        List<TimelineItem> data = new ArrayList<>();
        data.add(new StoryItem(TimelineAdapter.TIMELINE_STORY, makeStoryMockData()));
        data.add(new InduceUpdateItem(TimelineAdapter.TIMELINE_INDUCE_UPDATE));
        data.add(new PostItem(TimelineAdapter.TIMELINE_POST, R.drawable.img_post, getContext().getString(R.string.post_content1)));
        data.add(new PostItem(TimelineAdapter.TIMELINE_POST, getContext().getString(R.string.post_content2)));
        data.add(new PostItem(TimelineAdapter.TIMELINE_POST, R.drawable.img_boostcamp, getContext().getString(R.string.post_content3)));
        return data;
    }

    private List<Story> makeStoryMockData() {
        List<Story> data = new ArrayList<>();
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_ME, ""));
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_FRIEND, "문경현"));
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_FRIEND, "김준영"));
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_FRIEND, "나영일"));
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_FRIEND, "이지건"));
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_FRIEND, "정순호"));
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_FRIEND, "안주은"));
        data.add(new Story(TimelineAdapter.StoryAdapter.STORY_FRIEND, "최진주"));
        return data;
    }

}
