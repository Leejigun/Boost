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

public class EmptyFragment extends Fragment {

    public static EmptyFragment newInstance() {
        return new EmptyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_empty, container, false);
    }

}
