package com.academy.ferdowsi.training.core.api;


import com.academy.ferdowsi.training.core.MyApplication;
import com.academy.ferdowsi.training.video.model.AparatModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiMethod {

    ApiInterface mApiAll;

    public ApiMethod() {
        mApiAll = MyApplication.getApiComponent().getApiAll();
    }


    public void getVideos(String id, Observer<AparatModel> observer) {
        mApiAll.getVideos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}