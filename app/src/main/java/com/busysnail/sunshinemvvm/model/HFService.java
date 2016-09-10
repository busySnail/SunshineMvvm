package com.busysnail.sunshinemvvm.model;

import android.content.Context;


import com.busysnail.sunshinemvvm.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author: malong on 2016/9/1
 * email: malong_ilp@163.com
 * address: Xidian University
 */

public interface HFService {

    @GET("weather")
    Observable<WeatherAPI> hfWeather(@Query("city") String city, @Query("key") String key);

    class Factory {
        public static HFService create(Context context) {
            OkHttpClient client=new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L,TimeUnit.MILLISECONDS)
                    .writeTimeout(10000L,TimeUnit.MILLISECONDS)
                    .cache(new Cache(context.getCacheDir(),10*1024*1024))
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(Constants.BASIC_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(HFService.class);
        }
    }
}
