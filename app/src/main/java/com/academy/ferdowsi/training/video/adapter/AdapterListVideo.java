package com.academy.ferdowsi.training.video.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.academy.ferdowsi.training.R;
import com.academy.ferdowsi.training.dataBase.ManageDBFavoriteVideo;
import com.academy.ferdowsi.training.global.Constants;
import com.academy.ferdowsi.training.video.struct.StructListVideo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListVideo extends RecyclerView.Adapter<AdapterListVideo.ViewHolder> {
    private int itemLayout;
    private List<StructListVideo> dataListVideo;
    private Context mContext;
    private AdapterVideoHandler videoHandler;
    private Typeface currFont;

    public AdapterListVideo(int itemLayout, List<StructListVideo> dataListVideo,
                            AdapterVideoHandler videoHandler) {
        this.videoHandler = videoHandler;
        this.itemLayout = itemLayout;
        this.dataListVideo = dataListVideo;
        this.currFont = Constants.iranSansLight;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ManageDBFavoriteVideo manageDBFavoriteVideo = ManageDBFavoriteVideo.getInstance();
        String videoId = dataListVideo.get(position).getVideoId();
        String uid = dataListVideo.get(position).getUid();

        holder.tvVideoName.setText(dataListVideo.get(position).getVideoName());
        holder.tvSeenNumber.setText(
                mContext.getString(R.string.seen) + dataListVideo.get(position).getSeen());
        holder.tvDate.setText(dataListVideo.get(position).getDate());

        if (manageDBFavoriteVideo.checkIsDataAlreadyInDBorNot(videoId, uid)) {
            holder.ivFavorite.setImageResource(R.drawable.ic_video_start_fill);
        } else {
            holder.ivFavorite.setImageResource(R.drawable.ic_video_star_border);
        }
        int duration = (dataListVideo.get(position).getDuration());

        String result = String.format("%02d:%02d", duration / 60, duration % 60);
        holder.tvDuration.setText(result);

        holder.tvDate.setTypeface(currFont);
        holder.tvVideoName.setTypeface(currFont);
        holder.tvSeenNumber.setTypeface(currFont);
        holder.tvDuration.setTypeface(currFont);

        getImage(holder.ivImageVideo, dataListVideo.get(position).getUrlPerViewImage(),
                holder.pbProfileUser);
    }

    @Override
    public int getItemCount() {
        return dataListVideo.size();
    }

    private void getImage(final ImageView imageView, String url, final ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        Picasso.with(mContext).load(url).resize(
                (int) (mContext.getResources().getDimension(R.dimen.width_video_item_image)),
                (int) (mContext.getResources().getDimension(R.dimen.height_video_item_image)))
                .onlyScaleDown().placeholder(R.drawable.video_education).into(imageView,
                new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        super.onSuccess();
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        super.onError();
                    }
                });
    }

    public interface AdapterVideoHandler {
        void onItemListVideoClick(String videoName, String link);

        void onRemoveFavorite(int position);

        void onShare(String url);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivImageVideo, ivShare, ivFavorite;
        private TextView tvVideoName, tvDate, tvSeenNumber, tvDuration;
        private ProgressBar pbProfileUser;

        public ViewHolder(View itemView) {
            super(itemView);

            ivShare = itemView.findViewById(R.id.item_video_iv_share);
            ivFavorite = itemView.findViewById(R.id.item_video_iv_favorite);
            ivImageVideo = itemView.findViewById(R.id.item_video_iv_image_video);
            tvDate = itemView.findViewById(R.id.item_video_tv_date);
            tvSeenNumber = itemView.findViewById(R.id.tem_video_tv_seen_number);
            tvVideoName = itemView.findViewById(R.id.item_video_tv_name_video);
            tvDuration = itemView.findViewById(R.id.item_video_tv_duration);
            pbProfileUser = itemView.findViewById(R.id.item_video_pb_profile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoHandler.onItemListVideoClick(
                            dataListVideo.get(getLayoutPosition()).getVideoName(),
                            dataListVideo.get(getLayoutPosition()).getUid());
                }
            });

            ivShare.setOnClickListener(this);
            ivFavorite.setOnClickListener(this);
            ivFavorite.setTag(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_video_iv_favorite:
                    ViewHolder viewHolder = (ViewHolder) view.getTag();
                    manageFavorite(viewHolder);

                    break;
                case R.id.item_video_iv_share:
                    videoHandler.onShare(dataListVideo.get(getLayoutPosition()).getUrlVideo());
                    break;
            }
        }

        private void manageFavorite(ViewHolder viewHolder) {
            ManageDBFavoriteVideo manageDBFavoriteVideo = ManageDBFavoriteVideo.getInstance();

            boolean status = manageDBFavoriteVideo.checkIsDataAlreadyInDBorNot(
                    dataListVideo.get(getLayoutPosition()).getVideoId(),
                    dataListVideo.get(getLayoutPosition()).getUid());
            if (!status) {
                manageDBFavoriteVideo.insertFavorite(dataListVideo.get(getLayoutPosition()));
                viewHolder.ivFavorite.setImageResource(R.drawable.ic_video_start_fill);
            } else {
                manageDBFavoriteVideo.deleteFavorite(
                        dataListVideo.get(getLayoutPosition()).getVideoId());
                videoHandler.onRemoveFavorite(getLayoutPosition());
                viewHolder.ivFavorite.setImageResource(R.drawable.ic_video_star_border);
            }

        }
    }


}
