package com.academy.ferdowsi.training.core.api;


import android.annotation.SuppressLint;

import com.academy.ferdowsi.training.core.MyApplication;
import com.academy.ferdowsi.training.video.model.AparatModel;
import com.academy.ferdowsi.training.video.model.AparatVideoInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ApiMethod {

    ApiInterface mApiAll;

    public ApiMethod() {
        mApiAll = MyApplication.getApiComponent().getApiAll();
    }

    @SuppressLint("CheckResult")
    public void getVideos(String id, DisposableObserver<AparatModel> observer) {
        mApiAll.getVideos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

    @SuppressLint("CheckResult")
    public void getVideoInfo(String url, DisposableObserver<AparatVideoInfo> observer) {
        mApiAll.getVideoInfo(url)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }

}