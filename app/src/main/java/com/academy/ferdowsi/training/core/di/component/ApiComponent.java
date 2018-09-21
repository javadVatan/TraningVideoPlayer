package com.academy.ferdowsi.training.core.di.component;


import com.academy.ferdowsi.training.core.api.ApiInterface;
import com.academy.ferdowsi.training.core.di.module.ApiServerModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by JavadVatan on 8/20/2018
 */
@Singleton
@Component(modules = {ApiServerModule.class})
public interface ApiComponent {
    ApiInterface getApiAll();

    Retrofit getRetrofit();
}
