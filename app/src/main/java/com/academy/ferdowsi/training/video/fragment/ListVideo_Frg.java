package com.academy.ferdowsi.training.video.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.core.api.ApiMethod;
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.global.GlobalFunction;
import com.academy.ferdowsi.training.global.ProgressMyDialog;
import com.academy.ferdowsi.training.video.VideoActivity;
import com.academy.ferdowsi.training.video.adapter.AdapterListVideo;
import com.academy.ferdowsi.training.video.model.AparatModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class ListVideo_Frg extends Fragment implements AdapterListVideo.AdapterVideoHandler,
        View.OnClickListener {

    private static final String TAG_ID = "link";
    private static final String TAG_CATEGORY_NAME = "name";
    private static final int BIG_DIALOG = 0;
    private static final int SMALL_DIALOG = 1;
    private static int STYLE_DIALOG = 0;
    private static String currCatId;
    private View currView;
    private RecyclerView mRecyclerView;
    private ProgressBar pbBelowList;
    private AdapterListVideo adapterListVideo;
    private LinearLayoutManager mLayoutManager;
    private List<AparatModel.Videobyprofilecat> dataListVideo = new ArrayList<>();
    private boolean errorHappened;
    private ProgressMyDialog dialog;
    private LinearLayout ll_layoutError;
    private String nextLink;
    private String currID;
    private int positionEndOfList;
    private Context mContext;
    private String currCatName;
    private String currJsonArrayName;
    //----for pagination
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private String tag = "";
    private ApiMethod mApiMethod;

    public static Fragment newInstance(String link, String categoryName) {
        Fragment fragment = new ListVideo_Frg();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_ID, link);
        bundle.putString(TAG_CATEGORY_NAME, categoryName);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        currCatId = bundle.getString(TAG_ID);
        currCatName = bundle.getString(TAG_CATEGORY_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currView = inflater.inflate(R.layout.frg_list_video, container, false);
        mContext = getContext();

        STYLE_DIALOG = BIG_DIALOG;

        initVariable();

        setLinkJson(currCatId);
        executeAsyncTask();
        return currView;
    }

    private void initVariable() {
        mApiMethod = new ApiMethod();
        pbBelowList = currView.findViewById(R.id.pb_video_below_list);
        ll_layoutError = currView.findViewById(
                R.id.video_layout_erorr_ll_layout_error);

        mRecyclerView = currView.findViewById(R.id.frg_list_video_recycler_view);
        mLayoutManager = new LinearLayoutManager(mContext);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        adapterListVideo = new AdapterListVideo(R.layout.item_list_video, dataListVideo, this);
        mRecyclerView.setAdapter(adapterListVideo);
        positionEndOfList = dataListVideo.size();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            STYLE_DIALOG = SMALL_DIALOG;
                            if (nextLink != null && !nextLink.equals("null")) {
                                setLinkJson(nextLink);
                                executeAsyncTask();
                            }
                        }
                    }
                }
            }
        });

        initHeader();
    }

    private void initHeader() {

        TextView text = currView.findViewById(R.id./*header_title*/frg_list_video_tv_header);
        text.setTypeface(Constants.iranSans);
        text.setText(currCatName);
 /*
        int[] headerID = {R.id.header_action_navigation_back};
        for (int i = 0; i < headerID.length; i++) {
            ImageView imageView = (ImageView) currView.findViewById(headerID[i]);
            imageView.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(this);
        }*/
    }

    private void setAdapter() {
        mRecyclerView.setVisibility(View.VISIBLE);
        adapterListVideo.notifyItemRangeInserted(positionEndOfList,/* positionEndOfList -*/
                dataListVideo.size());
        positionEndOfList = dataListVideo.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
         /*   case R.id.header_action_navigation_back:
                getActivity().onBackPressed();
                break;*/
        }
    }


    private void executeAsyncTask() {
        if (!GlobalFunction.getInstance().isConnection(mContext)) {
            displayErrorMessage(getString(R.string.error_not_found_network));
        } else {
            callApiGetVideos();
        }
    }

    @Override
    public void onItemListVideoClick(String videoName, String link) {
        Fragment fragment_show_video = ShowVideo_Frg.newInstance(link);
        ((VideoActivity) getActivity()).switchFragment(fragment_show_video, true, "");
    }

    @Override
    public void onRemoveFavorite(int position) {

    }

    @Override
    public void onShare(String url) {
        GlobalFunction.getInstance().shareText(getContext(), url);
    }

    private void setLinkJson(String link) {
        currID = link;
    }

    public void displayErrorMessage(String strError) {
        TextView errorText = currView.findViewById(R.id.video_layout_erorr_tv_error);
        Button btnTryAgain = currView.findViewById(R.id.video_layout_erorr_btn_try_again);
        errorText.setTypeface(Constants.iranSansLight);
        errorText.setText(strError);
        mRecyclerView.setVisibility(View.GONE);
        ll_layoutError.setVisibility(View.VISIBLE);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_layoutError.setVisibility(View.GONE);
                STYLE_DIALOG = BIG_DIALOG;
                executeAsyncTask();
            }
        });

    }

    private void callApiGetVideos() {

        errorHappened = false;
        if (STYLE_DIALOG == BIG_DIALOG) {
            pbBelowList.setVisibility(View.GONE);
            dialog = new ProgressMyDialog(mContext, R.drawable.spinner);
            dialog.setText(mContext.getString(R.string.downloading_data));
            dialog.show(false);
        } else if (STYLE_DIALOG == SMALL_DIALOG) {
            pbBelowList.setVisibility(View.VISIBLE);
        }

        mApiMethod.getVideos(currID, new DisposableObserver<AparatModel>() {

            @Override
            public void onNext(AparatModel aparatModel) {
                dataListVideo.addAll(aparatModel.getVideobyprofilecat());
                nextLink = aparatModel.getUi().getPagingForward();
                setAdapter();
            }

            @Override
            public void onError(Throwable e) {
                displayErrorMessage(getString(R.string.error_un_expected));

            }

            @Override
            public void onComplete() {
                if (STYLE_DIALOG == SMALL_DIALOG) {
                    pbBelowList.setVisibility(View.GONE);
                } else dialog.close();

            }
        });
    }

}