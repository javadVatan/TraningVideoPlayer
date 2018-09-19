package com.academy.ferdowsi.training.video.fragment;

import android.content.Context;
import android.os.AsyncTask;
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
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.global.GlobalFunction;
import com.academy.ferdowsi.training.global.ProgressMyDialog;
import com.academy.ferdowsi.training.video.VideoActivity;
import com.academy.ferdowsi.training.video.adapter.AdapterListVideo;
import com.academy.ferdowsi.training.video.struct.StructListVideo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ListVideo_Frg extends Fragment implements AdapterListVideo.AdapterVideoHandler,
        View.OnClickListener {

    private static final String TAG_LINK = "link";
    private static final String TAG_CATEGORY_NAME = "name";
    private static final String TAG_API_MODE = "json_type";
    private static final int BIG_DIALOG = 0;
    private static final int SMALL_DIALOG = 1;
    private static final int API_BY_TAG = 0;
    private static final int API_BY_USER = 1;
    private static final String VIDEO_NAME = "title";
    private static final String DURATION = "duration";
    private static final String DATE = "sdate";
    private static final String SEEN_NUMBER = "visit_cnt";
    private static final String URL_SMALL_POSTER = "small_poster";
    private static final String VIDEO_ID = "id";
    private static final String UID = "uid";
    private static final String PAGINATION_UI = "ui";
    private static final String PAGINATION_FORWARD = "pagingForward";
    private static final String PAGINATION_BACK = "pagingBack";
    private static final String OFFSET = "o";
    private static final String TagLink = "tag";
    private static final String ForwardLink = "fl";
    private static final String LASTID = "id";
    private static final int len = 20;
    private static int STYLE_DIALOG = 0;
    // TODO: 11/6/2016  below code cemented for test by vatanDoost
    // private static String JSON_URL = ManageGPRS2.domain + "getVideo.php?";
    private static String JSON_URL;
    private final String URL_SHOW_VIDEO = "frame";
    private View currView;
    private RecyclerView mRecyclerView;
    private ProgressBar pbBelowList;
    private AdapterListVideo adapterListVideo;
    private LinearLayoutManager mLayoutManager;
    private List<StructListVideo> dataListVideo = new ArrayList<>();
    private boolean errorHappened;
    private ProgressMyDialog dialog;
    private LinearLayout ll_layoutError;
    private String nextLink;
    private String currLink;
    private int positionEndOfList;
    private Context mContext;
    private String currCategoryName;
    private int currApiMode;
    private String currJsonArrayName;
    //----for pagination
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int current = 0;
    private String tag = "";

    public static Fragment newInstance(String link, String categoryName, int apiMode) {
        Fragment fragment = new ListVideo_Frg();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_LINK, link);
        bundle.putString(TAG_CATEGORY_NAME, categoryName);
        bundle.putInt(TAG_API_MODE, apiMode);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        // TODO: 11/6/2016  below code cemented for test by vatanDoost
     /*   tag = bundle.getString(TAG_LINK);
        try {
            tag = URLEncoder.encode(tag, "utf-8");
        } catch (Exception exp) {
            exp.printStackTrace();
        }*/

        JSON_URL = bundle.getString(TAG_LINK);
        currCategoryName = bundle.getString(TAG_CATEGORY_NAME);
        currApiMode = bundle.getInt(TAG_API_MODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currView = inflater.inflate(R.layout.frg_list_video, container, false);
        mContext = getContext();

        STYLE_DIALOG = BIG_DIALOG;
        //---------------------------------------------------------------------------------------------------

      /*  final int ver = GlobalFunction.getInstance().getVersionCode(mContext);
        JSON_URL = JSON_URL + ManageGPRS2.codeVer_key + "=" + ver + "&" + ManageGPRS2.verType_key +
                "=" + VersionType.Ver_Type + "&" + TagLink + "=" + tag + "&" + OFFSET + "=";*/

        initVariable();
        /*  String link = JSON_URL + "0";
       setLinkJson(link);*/
        // TODO: 11/6/2016  below code for test by vatanDoost
        setLinkJson(JSON_URL);

        executeAsyncTask();
        return currView;
    }

    private void initVariable() {
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
                            if (!nextLink.equals("null")) {
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
        text.setText(currCategoryName);
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

    private void makeJSON(String finalJson, int currApiMode) {
        if (currApiMode == API_BY_TAG) {
            currJsonArrayName = "videobytag";
        } else if (currApiMode == API_BY_USER) {
            currJsonArrayName = "videobyuser";
        }
        try {
            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray(currJsonArrayName);

            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject object = parentArray.getJSONObject(i);

                StructListVideo viewModel = new StructListVideo(object.getString(VIDEO_ID),
                        object.getString(UID), object.getString(VIDEO_NAME),
                        object.getString(SEEN_NUMBER), object.getString(DATE),
                        object.getString(URL_SHOW_VIDEO),
                        (Integer.parseInt(object.getString(DURATION))),
                        object.getString(URL_SMALL_POSTER), (Integer.parseInt(object.getString(LASTID))));

                dataListVideo.add(viewModel);
            }
            JSONObject objectUi = parentObject.getJSONObject(PAGINATION_UI);
            nextLink = objectUi.getString(PAGINATION_FORWARD);

            if (!nextLink.equals("null")) {
                String params = (current + len) + "&" + ForwardLink + "=";
                nextLink = URLEncoder.encode(nextLink, "utf-8");
                nextLink = JSON_URL + params + nextLink;
                // TODO: 11/6/2016  below code for test by vatanDoost
                nextLink = JSON_URL + (current + len);

                current = (current + len);
                loading = true;
            }

        } catch (Exception e) {
            errorHappened = true;
            e.printStackTrace();
        }
    }

    private void executeAsyncTask() {
        if (!GlobalFunction.getInstance().isConnection(mContext)) {
            displayErrorMessage(getString(R.string.error_not_found_network));
        } else {
            (new DownloadJson()).execute();
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
        currLink = link;
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

    private class DownloadJson extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            errorHappened = false;
            if (STYLE_DIALOG == BIG_DIALOG) {
                pbBelowList.setVisibility(View.GONE);
                dialog = new ProgressMyDialog(mContext, R.drawable.spinner);
                dialog.setText(mContext.getString(R.string.downloading_data));
                dialog.show(false);
            } else if (STYLE_DIALOG == SMALL_DIALOG) {
                pbBelowList.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected String doInBackground(String... json_address) {
            HttpURLConnection connection = null;
            BufferedReader bReader = null;
            String finalJson = null;

            try {
                URL url = new URL(currLink);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                StringBuilder sBuilder = new StringBuilder();

                String line;
                while ((line = bReader.readLine()) != null) {
                    sBuilder.append(line).append("\n");
                }
                finalJson = sBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (bReader != null)
                    try {
                        bReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if (connection != null)
                    connection.disconnect();
            }
            return finalJson;
        }

        @Override
        protected void onPostExecute(String finalJson) {

            makeJSON(finalJson, currApiMode);

            if (STYLE_DIALOG == SMALL_DIALOG) {
                pbBelowList.setVisibility(View.GONE);
            }
            if (errorHappened) {
                displayErrorMessage(getString(R.string.error_un_expected));
            } else {
                setAdapter();
                if (STYLE_DIALOG == BIG_DIALOG) {
                    dialog.close();
                }
            }
        }
    }
}