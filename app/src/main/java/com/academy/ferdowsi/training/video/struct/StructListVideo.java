package com.academy.ferdowsi.training.video.struct;

public class StructListVideo {

    public int id;
    public String videoId, uid, videoName, seen, date, urlVideo, urlPerViewImage;
    public int duration, Lid;

    public StructListVideo(String videoId, String uid, String videoName, String seen, String date,
                           String urlVideo, int duration, String urlPerViewImage, int id) {

        this.videoId = videoId;
        this.uid = uid;
        this.videoName = videoName;
        this.seen = seen;
        this.date = date;
        this.urlVideo = urlVideo;
        this.duration = duration;
        this.urlPerViewImage = urlPerViewImage;
        this.Lid = id;

    }

    public StructListVideo() {

    }

    public String getVideoName() {
        return videoName;
    }

    public String getSeen() {
        return seen;
    }

    public String getDate() {
        return date;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public int getDuration() {
        return duration;
    }

    public String getUrlPerViewImage() {
        return urlPerViewImage;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getUid() {
        return uid;
    }

    public int getId() {
        return id;
    }
}
