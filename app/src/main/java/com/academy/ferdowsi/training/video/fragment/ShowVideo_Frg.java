package com.academy.ferdowsi.training.video.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.core.api.ApiMethod;
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.global.GlobalFunction;
import com.academy.ferdowsi.training.global.ProgressMyDialog;
import com.academy.ferdowsi.training.video.model.AparatVideoInfo;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import io.reactivex.observers.DisposableObserver;


public class ShowVideo_Frg extends Fragment {
    private static final String TAG_UUID = "Hash";
    private View currView;

    private String playLink, apiUrl;
    private LinearLayout ll_layoutError;
    private ProgressMyDialog progressMyDialog;
    private String currUuid = "";
    private ApiMethod mApiMethod;
    private SimpleExoPlayer mExoPlayer;
    private TrackSelector trackSelector = null;
    private PlayerView mExoPlayerView;

    public static Fragment newInstance(String uuid) {
        Fragment fragment = new ShowVideo_Frg();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_UUID, uuid);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        currUuid = bundle.getString(TAG_UUID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currView = inflater.inflate(R.layout.frg_show_videoview, container, false);

        initializer();

        return currView;
    }

    private void initializer() {
        initVariable();
        initExoPlayer();
        createLink();
        callApiGetVideoInfo();
    }

    private void initVariable() {
        mApiMethod = new ApiMethod();
        ll_layoutError = currView.findViewById(R.id.video_layout_erorr_ll_layout_error);
        mExoPlayerView = currView.findViewById(R.id.activity_play_track_exo_player);
    }

    /**
     * set ExoPlayer that use for playing audio and video
     */
    private void initExoPlayer() {
        Player.EventListener mExoListener = new Player.EventListener() {

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                dismissMyDialog();
                mExoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        };

        // Here is the Application wanna show Video

        if (trackSelector == null) {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        }
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

        //region exoPlayer listener
        mExoPlayer.addListener(mExoListener);
        //endregion


    }

    private void createLink() {
        String secretLink = getContext().getResources().getString(R.string.secret_link);
        String publishId = getContext().getResources().getString(R.string.publish_id);
        String privateKey = getContext().getResources().getString(R.string.private_key);

        String hash2 = GlobalFunction.getInstance().md5(currUuid + privateKey);
        apiUrl = Constants.API_VIDEO_HASH + currUuid + secretLink + hash2 + publishId;
    }

    private void callApiGetVideoInfo() {
        if (!GlobalFunction.getInstance().isConnection(getContext())) {
            displayErrorMessage(getString(R.string.error_not_found_network));
            return;
        }

        showMyDialog();

        mApiMethod.getVideoInfo(apiUrl, new DisposableObserver<AparatVideoInfo>() {
            @Override
            public void onNext(AparatVideoInfo aparatVideoInfo) {
                playLink = aparatVideoInfo.getVideoadvanceinfo().getFile_link();
                ShowVideo();
            }

            @Override
            public void onError(Throwable e) {
                displayErrorMessage(getString(R.string.error_un_expected));
                dismissMyDialog();
            }

            @Override
            public void onComplete() {

            }
        });
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


    private void ShowVideo() {
        ll_layoutError.setVisibility(View.GONE);
        mExoPlayer.prepare(buildMediaSource(Uri.parse(playLink)));
        mExoPlayerView.setPlayer(mExoPlayer);
    }

    /**
     * Create a MediaSource for ExoPlayer.
     *
     * @param uri
     * @return MediaSource.
     */
    private MediaSource buildMediaSource(Uri uri) {
        MediaSource mMediaSource;

        // manage online videoPlay
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "App Training"), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        mMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
        return mMediaSource;
    }


    private void displayErrorMessage(String strError) {
        dismissMyDialog();

        ll_layoutError.setVisibility(View.VISIBLE);
        TextView errorText = currView.findViewById(R.id.video_layout_erorr_tv_error);
        Button btnTryAgain = currView.findViewById(R.id.video_layout_erorr_btn_try_again);
        errorText.setTypeface(Constants.iranSansLight);
        errorText.setText(strError);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_layoutError.setVisibility(View.GONE);
                callApiGetVideoInfo();
            }
        });

    }

    public void stopVideo() {
        if (mExoPlayer != null && mExoPlayer.getPlayWhenReady())
            mExoPlayer.setPlayWhenReady(false);
    }
}
