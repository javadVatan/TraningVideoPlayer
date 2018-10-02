package com.academy.ferdowsi.training.video.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AparatModel {

    @Expose
    @SerializedName("ui")
    private Ui ui;
    @Expose
    @SerializedName("videobyprofilecat")
    private List<Videobyprofilecat> videobyprofilecat = new ArrayList<>();

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public List<Videobyprofilecat> getVideobyprofilecat() {
        return videobyprofilecat;
    }

    public void setVideobyprofilecat(List<Videobyprofilecat> videobyprofilecat) {
        this.videobyprofilecat = videobyprofilecat;
    }

    public static class Ui {
        @Expose
        @SerializedName("pagingBack")
        private String pagingBack;
        @Expose
        @SerializedName("pagingForward")
        private String pagingForward;

        public String getPagingBack() {
            return pagingBack;
        }

        public void setPagingBack(String pagingBack) {
            this.pagingBack = pagingBack;
        }

        public String getPagingForward() {
            return pagingForward;
        }

        public void setPagingForward(String pagingForward) {
            this.pagingForward = pagingForward;
        }
    }

    public static class Videobyprofilecat {
        @Expose
        @SerializedName("deleteurl")
        private String deleteurl;
        @Expose
        @SerializedName("video_date_status")
        private String video_date_status;
        @Expose
        @SerializedName("autoplay")
        private boolean autoplay;
        @Expose
        @SerializedName("official")
        private String official;
        @Expose
        @SerializedName("frame")
        private String frame;
        @Expose
        @SerializedName("sdate_timediff")
        private int sdate_timediff;
        @Expose
        @SerializedName("create_date")
        private String create_date;
        @Expose
        @SerializedName("sdate")
        private String sdate;
        @Expose
        @SerializedName("duration")
        private int duration;
        @Expose
        @SerializedName("profilePhoto")
        private String profilePhoto;
        @Expose
        @SerializedName("small_poster")
        private String small_poster;
        @Expose
        @SerializedName("big_poster")
        private String big_poster;
        @Expose
        @SerializedName("sender_name")
        private String sender_name;
        @Expose
        @SerializedName("process")
        private String process;
        @Expose
        @SerializedName("isHidden")
        private boolean isHidden;
        @Expose
        @SerializedName("uid")
        private String uid;
        @Expose
        @SerializedName("visit_cnt")
        private int visit_cnt;
        @Expose
        @SerializedName("userid")
        private String userid;
        @Expose
        @SerializedName("username")
        private String username;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("id")
        private String id;

        public String getDeleteurl() {
            return deleteurl;
        }

        public void setDeleteurl(String deleteurl) {
            this.deleteurl = deleteurl;
        }

        public String getVideo_date_status() {
            return video_date_status;
        }

        public void setVideo_date_status(String video_date_status) {
            this.video_date_status = video_date_status;
        }

        public boolean getAutoplay() {
            return autoplay;
        }

        public void setAutoplay(boolean autoplay) {
            this.autoplay = autoplay;
        }

        public String getOfficial() {
            return official;
        }

        public void setOfficial(String official) {
            this.official = official;
        }

        public String getFrame() {
            return frame;
        }

        public void setFrame(String frame) {
            this.frame = frame;
        }

        public int getSdate_timediff() {
            return sdate_timediff;
        }

        public void setSdate_timediff(int sdate_timediff) {
            this.sdate_timediff = sdate_timediff;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getSdate() {
            return sdate;
        }

        public void setSdate(String sdate) {
            this.sdate = sdate;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getProfilePhoto() {
            return profilePhoto;
        }

        public void setProfilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
        }

        public String getSmall_poster() {
            return small_poster;
        }

        public void setSmall_poster(String small_poster) {
            this.small_poster = small_poster;
        }

        public String getBig_poster() {
            return big_poster;
        }

        public void setBig_poster(String big_poster) {
            this.big_poster = big_poster;
        }

        public String getSender_name() {
            return sender_name;
        }

        public void setSender_name(String sender_name) {
            this.sender_name = sender_name;
        }

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public boolean getIsHidden() {
            return isHidden;
        }

        public void setIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getVisit_cnt() {
            return visit_cnt;
        }

        public void setVisit_cnt(int visit_cnt) {
            this.visit_cnt = visit_cnt;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
