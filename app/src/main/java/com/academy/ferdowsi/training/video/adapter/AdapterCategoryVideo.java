package com.academy.ferdowsi.training.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.video.struct.StructCategoryMaster;
import com.academy.ferdowsi.training.video.struct.StructCategorySub;

import java.util.ArrayList;


public class AdapterCategoryVideo extends RecyclerView.Adapter<AdapterCategoryVideo.ViewHolder>
        implements View.OnClickListener {
    private static final int TWO_ITEM = 2;
    private static final int ONE_ITEM = 1;
    private ArrayList<StructCategoryMaster> dataListVideo;
    private Context mContext;
    private AdapterCategoryVideoHandler categoryVideoHandler;

    public AdapterCategoryVideo(ArrayList<StructCategoryMaster> dataListVideo, AdapterCategoryVideoHandler categoryVideoHandler) {
        this.dataListVideo = dataListVideo;
        this.categoryVideoHandler = categoryVideoHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categroy_video,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvTitle.setText(dataListVideo.get(position).getTitle());

        for (int i = 0; i < dataListVideo.get(position).list.size(); i++) {
            int size = dataListVideo.get(position).list.size();
            if (size > TWO_ITEM) {
                holder.tvSub1.setText(dataListVideo.get(position).list.get(i).getCategoryName());
                holder.ivImage1.setImageResource(dataListVideo.get(position).list.get(i).getImage());
                holder.tvSub2.setText(dataListVideo.get(position).list.get(i + 1).getCategoryName());
                holder.ivImage2.setImageResource(dataListVideo.get(position).list.get(i + 1).getImage());
                holder.linearMore.setVisibility(View.VISIBLE);
            } else if (size == ONE_ITEM) {
                holder.tvSub1.setText(dataListVideo.get(position).list.get(i).getCategoryName());
                holder.ivImage1.setImageResource(dataListVideo.get(position).list.get(i).getImage());
                holder.linearItemSecond.setVisibility(View.GONE);
                holder.linearMore.setVisibility(View.GONE);
            } else if (size == TWO_ITEM) {
                holder.tvSub1.setText(dataListVideo.get(position).list.get(i).getCategoryName());
                holder.ivImage1.setImageResource(dataListVideo.get(position).list.get(i).getImage());
                holder.tvSub2.setText(dataListVideo.get(position).list.get(i + 1).getCategoryName());
                holder.ivImage2.setImageResource(dataListVideo.get(position).list.get(i + 1).getImage());
                holder.linearMore.setVisibility(View.GONE);
            }
            break;
        }

        holder.linearItemFirst.setOnClickListener(this);
        holder.linearItemSecond.setOnClickListener(this);
        holder.linearMore.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return dataListVideo.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        int pos = holder.getAdapterPosition();

        switch (view.getId()) {
            case R.id.item_cat_video_item_first:
                categoryVideoHandler.onItemCategoryClick(dataListVideo.get(pos).getList().get(0).getCategoryName(),
                        dataListVideo.get(pos).getList().get(0).getUrl(),
                        dataListVideo.get(pos).getList().get(0).getFlag());
                break;

            case R.id.item_cat_video_item_second:
                categoryVideoHandler.onItemCategoryClick(dataListVideo.get(pos).getList().get(1).getCategoryName(),
                        dataListVideo.get(pos).getList().get(1).getUrl(),
                        dataListVideo.get(pos).getList().get(1).getFlag());
                break;
            case R.id.item_cat_video_linear_more:
                categoryVideoHandler.onItemMoreClick(dataListVideo.get(pos).getTitle(),
                        dataListVideo.get(pos).getList());
                break;
        }
    }

    public interface AdapterCategoryVideoHandler {
        void onItemCategoryClick(String catName, String url, int apiMode);

        void onItemMoreClick(String title, ArrayList<StructCategorySub> structCategorySub_ArrayList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvSub1, tvSub2, tvMore;
        private ImageView ivImage1, ivImage2;
        private LinearLayout linearItemFirst, linearItemSecond, linearMore;

        public ViewHolder(View v) {
            super(v);
            linearItemFirst = v.findViewById(R.id.item_cat_video_item_first);
            linearItemSecond = v.findViewById(R.id.item_cat_video_item_second);
            linearMore = v.findViewById(R.id.item_cat_video_linear_more);
            tvTitle = v.findViewById(R.id.item_cat_video_tv_title);
            tvSub1 = v.findViewById(R.id.item_cat_video_tv_sub);
            tvSub2 = v.findViewById(R.id.item_cat_video_tv_sub2);
            tvMore = v.findViewById(R.id.item_cat_video_tv_more);
            ivImage1 = v.findViewById(R.id.item_cat_video_iv_image);
            ivImage2 = v.findViewById(R.id.item_cat_video_iv_image2);
            linearItemFirst.setTag(this);
            linearItemSecond.setTag(this);
            linearMore.setTag(this);

            tvTitle.setTypeface(Constants.iranSans);

            tvSub1.setTypeface(Constants.iranSansLight);
            tvSub2.setTypeface(Constants.iranSansLight);
            tvMore.setTypeface(Constants.iranSansLight);

        }
    }

}
