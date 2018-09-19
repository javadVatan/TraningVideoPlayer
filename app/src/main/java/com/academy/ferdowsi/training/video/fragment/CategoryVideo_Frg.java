package com.academy.ferdowsi.training.video.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.video.VideoActivity;
import com.academy.ferdowsi.training.video.adapter.AdapterCategoryVideo;
import com.academy.ferdowsi.training.video.struct.StructCategoryMaster;
import com.academy.ferdowsi.training.video.struct.StructCategorySub;

import java.util.ArrayList;


public class CategoryVideo_Frg extends Fragment implements AdapterCategoryVideo.AdapterCategoryVideoHandler {

    private View currView;
    private ArrayList<StructCategoryMaster> dataListVideo = new ArrayList<>();
    private ArrayList<StructCategorySub> listStructCategorySub_;
    private Context mContext;

    public static Fragment newInstance() {
        return new CategoryVideo_Frg();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currView = inflater.inflate(R.layout.frg_category_video, container, false);
        mContext = getContext();
        initData();
        initList();

        return currView;
    }

    private void initData() {
        int[] imgId = {R.drawable.video_mobile, R.drawable.video_table, R.drawable.video_mobil_accessories,
                R.drawable.video_magics, R.drawable.video_personal_camputer, R.drawable.video_laptop,
                R.drawable.video_apps, R.drawable.video_personal_camputer_accessories,
                R.drawable.video_education, R.drawable.video_education, R.drawable.video_education,
                R.drawable.video_education, R.drawable.video_education, R.drawable.video_education,
                R.drawable.video_education, R.drawable.video_education, R.drawable.video_education,
                R.drawable.video_education, R.drawable.video_education, R.drawable.video_education,
                R.drawable.video_education, R.drawable.video_education, R.drawable.video_gadgets,
                R.drawable.video_cartoon, R.drawable.video_documentary};

        int j = 0;
        int arraySize = 0;
        ArrayList<StructCategorySub> arraySub;

        for (int i = 0; i < mContext.getResources().getStringArray(R.array.video_title).length; i++) {
            arraySub = new ArrayList<>();
            for (; j < mContext.getResources().getIntArray(R.array.video_count_title)[i] + arraySize; j++) {
                StructCategorySub structCategorySub = new StructCategorySub(mContext.getResources().getStringArray(R.array.video_category_name)[j],
                        imgId[j], mContext.getResources().getStringArray(R.array.video_links)[j],
                        mContext.getResources().getIntArray(R.array.video_api_mode)[j]);
                arraySub.add(structCategorySub);
            }
            StructCategoryMaster structCategoryMaster = new StructCategoryMaster(mContext.getResources().getStringArray
                    (R.array.video_title)[i], arraySub);
            dataListVideo.add(structCategoryMaster);
            arraySize += mContext.getResources().getIntArray(R.array.video_count_title)[i];
        }
    }

    private void initList() {
        RecyclerView mRecyclerView = currView.findViewById(
                R.id.fragment_category_video_recycler_view);
        AdapterCategoryVideo adapterCategoryVideo = new AdapterCategoryVideo(dataListVideo, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapterCategoryVideo);
    }

    @Override
    public void onItemCategoryClick(String catName, String url, int apiMode) {
        //start video list frg
        Fragment fragment = ListVideo_Frg.newInstance(url, catName, apiMode);
        ((VideoActivity) getActivity()).switchFragment(fragment, true, "");
    }

    @Override
    public void onItemMoreClick(String title, ArrayList<StructCategorySub> structCategorySub_ArrayList) {
        Fragment fragment = AllCategoryVideo_Frg.newInstance();
        ((AllCategoryVideo_Frg) fragment).setData(title, structCategorySub_ArrayList);
        ((VideoActivity) getActivity()).switchFragment(fragment, true, "");
    }
}
