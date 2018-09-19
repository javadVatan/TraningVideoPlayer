package com.academy.ferdowsi.training.video.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.global.GlobalFunction;
import com.academy.ferdowsi.training.global.ProgressMyDialog;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ShowVideo_Frg extends Fragment implements MediaPlayer.OnPreparedListener {
    private static final String TAG_LINK = "Hash";
    private static final String FILE_LINK = "file_link";
    private final String JSON_ARRAY_NAME = "videoadvanceinfo";
    private View view;
    private String jsonLink = "http://aparat.com/etc/api/videoAdvanceInfo/videohash/";
    private String jsonLink2 = "/secid/mojemobile/seckey/";
    private String publishid = "/publisherid/511560";
    private String playLink;
    private VideoView wvShowVideo;
    private LinearLayout ll_layoutError;
    private ProgressMyDialog progressMyDialog;
    private String hashvideo = "";
    private Context context;
    private String privateKey = "381377b5a83c07caef0448400550fa55";
    private boolean errorHappened;


    public static Fragment newInstance(String link) {
        Fragment fragment = new ShowVideo_Frg();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_LINK, link);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        hashvideo = bundle.getString(TAG_LINK);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_show_videoview, container, false);
        context = getContext();
        wvShowVideo = view.findViewById(R.id.frg_show_video_VideoView);
        ll_layoutError = view.findViewById(R.id.video_layout_erorr_ll_layout_error);
/*        EncryptDescrypt encryptDescrypt = new EncryptDescrypt();
        String hash2 = encryptDescrypt.getHexMd5(hashvideo+privateKey);
        jsonLink = jsonLink+hashvideo+jsonLink2+hash2+publishid;*/


        // TODO: 11/6/2016  below code cemented for test by vatanDoost

        // showMyDialog();
        // executeAsyncTask();
        wvShowVideo.setVideoURI(Uri.parse(hashvideo));

        return view;
    }

    private void executeAsyncTask() {
        if (!GlobalFunction.getInstance().isConnection(context)) {
            displayErrorMessage(getString(R.string.error_not_found_network));
        } else {
            (new DownloadJson()).execute();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        dismissMyDialog();
        wvShowVideo.start();
    }

    private void makeJSON(String finalJson) {
        try {
            JSONObject parentObject = new JSONObject(finalJson);
            JSONObject parentArray = parentObject.getJSONObject(JSON_ARRAY_NAME);

//            for (int i = 0; i < parentArray.length(); i++) {
//                JSONObject object = parentArray.getJSONObject(i);

            playLink = parentArray.getString(FILE_LINK);

//            }

        } catch (Exception e) {
            errorHappened = true;
            e.printStackTrace();
        }
    }

    private void showMyDialog() {
        if (progressMyDialog != null) {
            dismissMyDialog();
        }

        progressMyDialog = new ProgressMyDialog(getContext(), R.drawable.spinner);
        progressMyDialog.show(false);
    }

    private void dismissMyDialog() {
        if (progressMyDialog != null) {
            progressMyDialog.close();
            progressMyDialog = null;

        }
    }

    private void loadVideo() {

        if (GlobalFunction.getInstance().isConnection(getContext())) {
            ShowVide();
        } else {
            displayErrorMessage(getContext().getString(R.string.error_not_found_network));
        }
    }

    private void ShowVide() {
        ll_layoutError.setVisibility(View.GONE);
        wvShowVideo.setVisibility(View.VISIBLE);

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    context);
            mediacontroller.setAnchorView(wvShowVideo);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(playLink);
            wvShowVideo.setMediaController(mediacontroller);
            wvShowVideo.setVideoURI(video);
        } catch (Exception e) {
            Log.i("Erorr", e.getMessage());
            e.printStackTrace();
            dismissMyDialog();
        }
        wvShowVideo.requestFocus();
        wvShowVideo.setOnPreparedListener(this);
        wvShowVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                wvShowVideo.stopPlayback();
                dismissMyDialog();
                //((Activity)context).finish();
                return false;
            }
        });
    }

    public void stopVideo() {
//        wvShowVideo.destroy();

        try {

            wvShowVideo.stopPlayback();

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void displayErrorMessage(String strError) {
        wvShowVideo.setVisibility(View.INVISIBLE);
        dismissMyDialog();

        ll_layoutError.setVisibility(View.VISIBLE);
        TextView errorText = view.findViewById(R.id.video_layout_erorr_tv_error);
        Button btnTryAgain = view.findViewById(R.id.video_layout_erorr_btn_try_again);
        errorText.setTypeface(Constants.iranSansLight);
        errorText.setText(strError);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_layoutError.setVisibility(View.GONE);
                loadVideo();
            }
        });

    }

    private class DownloadJson extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... json_address) {
            HttpURLConnection connection = null;
            BufferedReader bReader = null;
            String finalJson = null;

            try {
                URL url = new URL(jsonLink);
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

            makeJSON(finalJson);

            if (errorHappened) {
                displayErrorMessage(getString(R.string.error_un_expected));
            } else {
                dismissMyDialog();
                loadVideo();
//                setAdapter();
            }
        }
    }

}
