package com.academy.ferdowsi.training.video.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AparatVideoInfo {

    @Expose
    @SerializedName("videoadvanceinfo")
    private Videoadvanceinfo videoadvanceinfo;

    public Videoadvanceinfo getVideoadvanceinfo() {
        return videoadvanceinfo;
    }

    public void setVideoadvanceinfo(Videoadvanceinfo videoadvanceinfo) {
        this.videoadvanceinfo = videoadvanceinfo;
    }

    public static class Videoadvanceinfo {
        @Expose
        @SerializedName("like_cnt")
        private int like_cnt;
        @Expose
        @SerializedName("can_download")
        private boolean can_download;
        @Expose
        @SerializedName("cost_type")
        private Cost_type cost_type;
        @Expose
        @SerializedName("watch_action")
        private Watch_action watch_action;
        @Expose
        @SerializedName("size")
        private String size;
        @Expose
        @SerializedName("has_comment_txt")
        private String has_comment_txt;
        @Expose
        @SerializedName("has_comment")
        private String has_comment;
        @Expose
        @SerializedName("playeradvertcornel")
        private String playeradvertcornel;
        @Expose
        @SerializedName("share")
        private Share share;
        @Expose
        @SerializedName("like")
        private Like like;
        @Expose
        @SerializedName("playeradvert_arr")
        private Playeradvert_arr playeradvert_arr;
        @Expose
        @SerializedName("playeradvert_vast")
        private String playeradvert_vast;
        @Expose
        @SerializedName("playeradvert_time")
        private int playeradvert_time;
        @Expose
        @SerializedName("playeradvert_src")
        private String playeradvert_src;
        @Expose
        @SerializedName("playeradvert")
        private String playeradvert;
        @Expose
        @SerializedName("hls")
        private Hls hls;
        @Expose
        @SerializedName("videovisitcall_time")
        private int videovisitcall_time;
        @Expose
        @SerializedName("videovisit")
        private String videovisit;
        @Expose
        @SerializedName("file_link_all")
        private List<File_link_all> file_link_all;
        @Expose
        @SerializedName("file_link")
        private String file_link;
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
        @SerializedName("cat_name")
        private String cat_name;
        @Expose
        @SerializedName("cat_id")
        private int cat_id;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("tag_str")
        private String tag_str;
        @Expose
        @SerializedName("tags")
        private List<Tags> tags;
        @Expose
        @SerializedName("flv_name")
        private String flv_name;
        @Expose
        @SerializedName("official")
        private String official;
        @Expose
        @SerializedName("video_cnt")
        private String video_cnt;
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
        private String duration;
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

        public int getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }

        public boolean getCan_download() {
            return can_download;
        }

        public void setCan_download(boolean can_download) {
            this.can_download = can_download;
        }

        public Cost_type getCost_type() {
            return cost_type;
        }

        public void setCost_type(Cost_type cost_type) {
            this.cost_type = cost_type;
        }

        public Watch_action getWatch_action() {
            return watch_action;
        }

        public void setWatch_action(Watch_action watch_action) {
            this.watch_action = watch_action;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getHas_comment_txt() {
            return has_comment_txt;
        }

        public void setHas_comment_txt(String has_comment_txt) {
            this.has_comment_txt = has_comment_txt;
        }

        public String getHas_comment() {
            return has_comment;
        }

        public void setHas_comment(String has_comment) {
            this.has_comment = has_comment;
        }

        public String getPlayeradvertcornel() {
            return playeradvertcornel;
        }

        public void setPlayeradvertcornel(String playeradvertcornel) {
            this.playeradvertcornel = playeradvertcornel;
        }

        public Share getShare() {
            return share;
        }

        public void setShare(Share share) {
            this.share = share;
        }

        public Like getLike() {
            return like;
        }

        public void setLike(Like like) {
            this.like = like;
        }

        public Playeradvert_arr getPlayeradvert_arr() {
            return playeradvert_arr;
        }

        public void setPlayeradvert_arr(Playeradvert_arr playeradvert_arr) {
            this.playeradvert_arr = playeradvert_arr;
        }

        public String getPlayeradvert_vast() {
            return playeradvert_vast;
        }

        public void setPlayeradvert_vast(String playeradvert_vast) {
            this.playeradvert_vast = playeradvert_vast;
        }

        public int getPlayeradvert_time() {
            return playeradvert_time;
        }

        public void setPlayeradvert_time(int playeradvert_time) {
            this.playeradvert_time = playeradvert_time;
        }

        public String getPlayeradvert_src() {
            return playeradvert_src;
        }

        public void setPlayeradvert_src(String playeradvert_src) {
            this.playeradvert_src = playeradvert_src;
        }

        public String getPlayeradvert() {
            return playeradvert;
        }

        public void setPlayeradvert(String playeradvert) {
            this.playeradvert = playeradvert;
        }

        public Hls getHls() {
            return hls;
        }

        public void setHls(Hls hls) {
            this.hls = hls;
        }

        public int getVideovisitcall_time() {
            return videovisitcall_time;
        }

        public void setVideovisitcall_time(int videovisitcall_time) {
            this.videovisitcall_time = videovisitcall_time;
        }

        public String getVideovisit() {
            return videovisit;
        }

        public void setVideovisit(String videovisit) {
            this.videovisit = videovisit;
        }

        public List<File_link_all> getFile_link_all() {
            return file_link_all;
        }

        public void setFile_link_all(List<File_link_all> file_link_all) {
            this.file_link_all = file_link_all;
        }

        public String getFile_link() {
            return file_link;
        }

        public void setFile_link(String file_link) {
            this.file_link = file_link;
        }

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

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTag_str() {
            return tag_str;
        }

        public void setTag_str(String tag_str) {
            this.tag_str = tag_str;
        }

        public List<Tags> getTags() {
            return tags;
        }

        public void setTags(List<Tags> tags) {
            this.tags = tags;
        }

        public String getFlv_name() {
            return flv_name;
        }

        public void setFlv_name(String flv_name) {
            this.flv_name = flv_name;
        }

        public String getOfficial() {
            return official;
        }

        public void setOfficial(String official) {
            this.official = official;
        }

        public String getVideo_cnt() {
            return video_cnt;
        }

        public void setVideo_cnt(String video_cnt) {
            this.video_cnt = video_cnt;
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

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
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

    public static class Cost_type {
        @Expose
        @SerializedName("type")
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Watch_action {
        @Expose
        @SerializedName("type")
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Share {
        @Expose
        @SerializedName("status")
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class Like {
        @Expose
        @SerializedName("link")
        private String link;
        @Expose
        @SerializedName("status")
        private String status;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class Playeradvert_arr {
        @Expose
        @SerializedName("link")
        private String link;
        @Expose
        @SerializedName("type")
        private String type;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Hls {
        @Expose
        @SerializedName("link")
        private String link;
        @Expose
        @SerializedName("enable")
        private boolean enable;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public boolean getEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }

    public static class File_link_all {
        @Expose
        @SerializedName("urls")
        private List<String> urls;
        @Expose
        @SerializedName("profile")
        private String profile;

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }
    }

    public static class Tags {
        @Expose
        @SerializedName("cnt")
        private String cnt;
        @Expose
        @SerializedName("name")
        private String name;

        public String getCnt() {
            return cnt;
        }

        public void setCnt(String cnt) {
            this.cnt = cnt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
