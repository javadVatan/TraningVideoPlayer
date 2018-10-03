package com.academy.ferdowsi.training.core.api;

import com.academy.ferdowsi.training.video.model.AparatModel;
import com.academy.ferdowsi.training.video.model.AparatVideoInfo;

import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

@Singleton
public interface ApiInterface {

    //region  ..
    //https://www.aparat.com/etc/api/videobyprofilecat/usercat/108575/username/goharimanesh
    @GET("videobyprofilecat/usercat/{id}/username/goharimanesh")
    Observable<AparatModel> getVideos(@Path("id") String id);

    @GET
    Observable<AparatVideoInfo> getVideoInfo(@Url String url);


}
