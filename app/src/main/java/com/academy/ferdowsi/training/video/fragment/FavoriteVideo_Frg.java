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
import com.academy.ferdowsi.training.dataBase.ManageDBFavoriteVideo;
import com.academy.ferdowsi.training.global.GlobalFunction;
import com.academy.ferdowsi.training.video.VideoActivity;
import com.academy.ferdowsi.training.video.adapter.AdapterListVideo;
import com.academy.ferdowsi.training.video.model.AparatModel;

import java.util.ArrayList;
import java.util.List;


public class FavoriteVideo_Frg extends Fragment implements AdapterListVideo.AdapterVideoHandler {


    private View currView;
    private List<AparatModel.Videobyprofilecat> dataListVideo = new ArrayList<>();
    private Context mContext;
    private AdapterListVideo adapterListVideo;

    public static Fragment newInstance() {
        return new FavoriteVideo_Frg();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currView = inflater.inflate(R.layout.frg_favorite_video, container, false);
        mContext = getContext();

        return currView;
    }

    private void getDataFromDataBase() {
        ManageDBFavoriteVideo manageDBFavoriteVideo = ManageDBFavoriteVideo.getInstance();
        dataListVideo.clear();
        AparatModel mAparatModel = manageDBFavoriteVideo.getAllFavoriteInfo();
        dataListVideo.addAll(mAparatModel.getVideobyprofilecat());
    }

    private void initList() {
        RecyclerView mRecyclerView = currView.findViewById(
                R.id.fragment_favorite_video_recycler_view);
        adapterListVideo = new AdapterListVideo(R.layout.item_list_video, dataListVideo, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapterListVideo);
    }

    @Override
    public void onItemListVideoClick(String videoName, String link) {
        Fragment fragment_show_video = ShowVideo_Frg.newInstance(link);
        ((VideoActivity) getActivity()).switchFragment(fragment_show_video, true, "");

    }

    @Override
    public void onRemoveFavorite(int position) {
        dataListVideo.remove(position);
        adapterListVideo.notifyItemRemoved(position);
    }

    @Override
    public void onShare(String url) {
        GlobalFunction.getInstance().shareText(getContext(), url);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromDataBase();
        initList();
    }
}
