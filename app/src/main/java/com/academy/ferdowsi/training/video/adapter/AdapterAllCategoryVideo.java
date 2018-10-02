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
import com.academy.ferdowsi.training.video.struct.StructCategorySub;

import java.util.ArrayList;


public class AdapterAllCategoryVideo extends RecyclerView.Adapter<AdapterAllCategoryVideo.ViewHolder1>
        implements View.OnClickListener {

    private ArrayList<StructCategorySub> dataList;
    private Context mContext;
    private AdapterAllCategoryVideoHandler categoryAllHandler;

    public AdapterAllCategoryVideo(ArrayList<StructCategorySub> dataList,
                                   AdapterAllCategoryVideoHandler categoryAllHandler) {
        this.dataList = dataList;
        this.categoryAllHandler = categoryAllHandler;
    }

    @Override
    public void onClick(View view) {
        ViewHolder1 holder = (ViewHolder1) view.getTag();
        int pos = holder.getAdapterPosition();

        switch (view.getId()) {
            case R.id.item_all_cat_video_ll_root:
                categoryAllHandler.onItemAllCategoryClick(dataList.get(pos).getCategoryName(),
                        dataList.get(pos).getUrl());
                break;
        }
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_categroy_video,
                parent, false);
        return new ViewHolder1(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {
        holder.tvTitle.setText(dataList.get(position).getCategoryName());
        holder.ivIcon.setImageResource(dataList.get(position).getImage());
        holder.linearItemFirst.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface AdapterAllCategoryVideoHandler {
        void onItemAllCategoryClick(String catName, String url);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView ivIcon;
        private LinearLayout linearItemFirst;

        public ViewHolder1(View v) {
            super(v);
            linearItemFirst = v.findViewById(R.id.item_all_cat_video_ll_root);
            tvTitle = v.findViewById(R.id.item_all_cat_video_tv_title);
            ivIcon = v.findViewById(R.id.item_all_cat_video_iv_icon);

            linearItemFirst.setTag(this);
            tvTitle.setTypeface(Constants.iranSansLight);
        }
    }
}
