package com.academy.ferdowsi.training.core.di.module;


import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by JavadVatan on 8/20/2018
 */

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    public OkHttpClient okHttpClient(/* Authenticator authenticator,*/ Cache cache, Context context) {
        return new OkHttpClient.Builder()
                //     .authenticator(authenticator)
                .cache(cache)
                .readTimeout(35, TimeUnit.SECONDS)
                .writeTimeout(35, TimeUnit.SECONDS)
                .connectTimeout(35, TimeUnit.SECONDS)
                .build();
    }

/*    @Provides
    public Authenticator authenticator() {
        return (route, response) -> {
            if (new ApiMethod().getLocalData().isLogin()) {
                new ApiMethod().refreshToken();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String token = new ApiMethod().getLocalData().getToken();
                return response.request().newBuilder()
                        .removeHeader("Authorization")
                        .addHeader("Authorization", token)
                        .build();

            }
            return null;
        };


    }*/

    @Provides
    public Cache cache(File cachefile) {
        return new Cache(cachefile, 10 * 1000 * 1000);//10MB cache;
    }

    @Provides
    public File file(Context context) {
        File file_cache = new File(context.getCacheDir(), "okhttp_cache");
        file_cache.mkdirs();
        return file_cache;
    }
}
