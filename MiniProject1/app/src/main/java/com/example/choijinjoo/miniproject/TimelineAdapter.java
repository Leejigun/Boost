package com.example.choijinjoo.miniproject;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.choijinjoo.miniproject.model.PostItem;
import com.example.choijinjoo.miniproject.model.Story;
import com.example.choijinjoo.miniproject.model.StoryItem;
import com.example.choijinjoo.miniproject.model.TimelineItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by choijinjoo on 2017. 7. 4..
 */

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<TimelineItem> timelineItems = null;
    Context context = null;
    ItemClickedListener listener;

    public static final int TIMELINE_STORY = 101;
    public static final int TIMELINE_INDUCE_UPDATE = 102;
    public static final int TIMELINE_POST = 103;

    interface ItemClickedListener {
        void Clicked(String msg);
    }

    public TimelineAdapter(List<TimelineItem> timelineItems, Context context, ItemClickedListener listener) {
        this.timelineItems = timelineItems;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TIMELINE_STORY:
                return new StoriesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_story, parent, false), context);
            case TIMELINE_INDUCE_UPDATE:
                return new InduceUpdateViewHolder(LayoutInflater.from(context).inflate(R.layout.item_induce_update, parent, false), context);
            case TIMELINE_POST:
                return new PostViewHolder(LayoutInflater.from(context).inflate(R.layout.item_post, parent, false), context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TIMELINE_STORY:
                ((StoriesViewHolder) holder).bindView(timelineItems.get(position), listener);
                break;
            case TIMELINE_INDUCE_UPDATE:
                ((InduceUpdateViewHolder) holder).bindView(timelineItems.get(position), listener);
                break;
            case TIMELINE_POST:
                ((PostViewHolder) holder).bindView(timelineItems.get(position), listener);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return timelineItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return timelineItems.get(position).getViewType();
    }


    /*
     *    Timeline Viewholder
     */

    class BaseViewHolder extends RecyclerView.ViewHolder {
        Context context;

        public BaseViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
        }
    }

    class StoriesViewHolder extends BaseViewHolder {
        @BindView(R.id.recvStories)
        RecyclerView recvStories;

        public StoriesViewHolder(View itemView, Context context) {
            super(itemView, context);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(TimelineItem timelineItem, ItemClickedListener listener) {
            recvStories.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));
            recvStories.setAdapter(new StoryAdapter(((StoryItem) timelineItem).getStories(), context, listener));

        }
    }

    class InduceUpdateViewHolder extends BaseViewHolder {
        @BindView(R.id.containerInduceUpdate)
        LinearLayout containerInduceUpdate;

        public InduceUpdateViewHolder(View itemView, Context context) {
            super(itemView, context);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(TimelineItem timelineItem, final ItemClickedListener listener) {
            containerInduceUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Clicked(context.getString(R.string.toastmessage_induceupdate));
                }
            });

        }
    }

    class PostViewHolder extends BaseViewHolder {
        @BindView(R.id.imgvImage)
        ImageView imgvImage;
        @BindView(R.id.txtvContent)
        TextView txtvContent;
        @BindView(R.id.btnLike)
        LinearLayout btnLike;
        @BindView(R.id.txtvLike)
        TextView txtvLike;
        @BindView(R.id.imgvLike)
        ImageView imgvLike;
        @BindView(R.id.btnComment)
        LinearLayout btnComment;
        @BindView(R.id.btnShare)
        LinearLayout btnShare;


        public PostViewHolder(View itemView, Context context) {
            super(itemView, context);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(TimelineItem timelineItem, final ItemClickedListener listener) {
            initLayout();

            if (((PostItem) timelineItem).getContent() != null)
                txtvContent.setText(((PostItem) timelineItem).getContent());
            if (((PostItem) timelineItem).getImgId() != null) {
                imgvImage.setVisibility(View.VISIBLE);
                imgvImage.setImageResource(((PostItem) timelineItem).getImgId());
                imgvImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.Clicked(context.getString(R.string.toastmessage_postimage));
                    }
                });
            }else{
                imgvImage.setVisibility(View.GONE);
            }

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtvLike.setSelected(true);
                    imgvLike.setSelected(true);
                    listener.Clicked(context.getString(R.string.toastmessage_like));
                }
            });
            btnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Clicked(context.getString(R.string.toastmessage_comment));
                }
            });
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Clicked(context.getString(R.string.toastmessage_share));
                }
            });
        }

        private void initLayout() {
            imgvImage.setVisibility(View.INVISIBLE);
        }
    }

    /*
     *    Story Adapter
     */

    class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<Story> stories;
        Context context;
        ItemClickedListener listener;

        public static final int STORY_ME = 201;
        public static final int STORY_FRIEND = 202;


        public StoryAdapter(List<Story> story, Context context, ItemClickedListener listener) {
            this.stories = story;
            this.context = context;
            this.listener = listener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new StoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false), context);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((StoryViewHolder) holder).bindView(stories.get(position), listener);
        }

        @Override
        public int getItemCount() {
            return stories.size();
        }

    }

    /*
     *    Story Viewholder
     */


    class StoryViewHolder extends BaseViewHolder {
        @BindView(R.id.containerFriend)
        RelativeLayout containerFriend;
        @BindView(R.id.containerCamera)
        RelativeLayout containerCamera;
        @BindView(R.id.txtvName)
        TextView txtvName;


        public StoryViewHolder(View itemView, Context context) {
            super(itemView, context);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(Story story, ItemClickedListener listener) {
            initLayout();
            switch (story.getViewType()) {
                case StoryAdapter.STORY_ME:
                    bindMeLayout(listener);
                    break;
                case StoryAdapter.STORY_FRIEND:
                    bindFriendLayout(story, listener);
                    break;
            }
        }

        private void initLayout() {
            containerFriend.setVisibility(View.INVISIBLE);
            containerCamera.setVisibility(View.INVISIBLE);
            containerFriend.setOnClickListener(null);
            containerCamera.setOnClickListener(null);
        }

        private void bindMeLayout(final ItemClickedListener listener) {
            containerCamera.setVisibility(View.VISIBLE);
            containerCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Clicked(context.getString(R.string.toastmessage_story));
                }
            });
        }

        private void bindFriendLayout(Story story, final ItemClickedListener listener) {
            containerFriend.setVisibility(View.VISIBLE);
            if (story.getName() != null)
                txtvName.setText(story.getName());
            containerFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Clicked(context.getString(R.string.toastmessage_other_story));
                }
            });

        }

    }

}
