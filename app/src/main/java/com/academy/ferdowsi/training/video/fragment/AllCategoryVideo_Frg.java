package com.academy.ferdowsi.training.video.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.video.VideoActivity;
import com.academy.ferdowsi.training.video.adapter.AdapterAllCategoryVideo;
import com.academy.ferdowsi.training.video.struct.StructCategorySub;

import java.util.ArrayList;


public class AllCategoryVideo_Frg extends Fragment
        implements AdapterAllCategoryVideo.AdapterAllCategoryVideoHandler {

    private View currView;
    private String currTitle;
    private ArrayList<StructCategorySub> currArrayList;
    private Context mContext;

    public static Fragment newInstance() {
        return new AllCategoryVideo_Frg();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currView = inflater.inflate(R.layout.frg_all_category_video, container, false);
        mContext = getContext();

        initList();
        initHeader();

        return currView;
    }

    private void initList() {
        RecyclerView mRecyclerView = currView.findViewById(
                R.id.frg_all_category_video_recycler_view);
        AdapterAllCategoryVideo adapterAllCat = new AdapterAllCategoryVideo(currArrayList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapterAllCat);
    }

    private void initHeader() {
        TextView tvTitle = currView.findViewById(R.id.frg_all_category_video_tv_header);
        tvTitle.setText(currTitle);
        tvTitle.setTypeface(Constants.iranSans);
    }

    public void setData(String title, ArrayList<StructCategorySub> structCategorySub_ArrayList) {
        currTitle = title;
        currArrayList = structCategorySub_ArrayList;
    }

    @Override
    public void onItemAllCategoryClick(String catName, String url) {
        //start video list fr
        Fragment fragment = ListVideo_Frg.newInstance(url, catName);
        ((VideoActivity) getActivity()).switchFragment(fragment, true, "");
    }
}
