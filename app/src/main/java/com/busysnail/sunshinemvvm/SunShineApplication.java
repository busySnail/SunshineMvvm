package com.busysnail.sunshinemvvm;

import android.app.Application;
import android.content.Context;

import com.busysnail.sunshinemvvm.model.HFService;
import com.squareup.leakcanary.LeakCanary;

import rx.Scheduler;
import rx.schedulers.Schedulers;


public class SunShineApplication extends Application {
    private HFService hfService;
    private Scheduler defaultSubscribeScheduler;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

    public static SunShineApplication get(Context context) {
        return (SunShineApplication) context.getApplicationContext();
    }

    public HFService getHFService() {
        if (hfService == null) {
            hfService = HFService.Factory.create(this);
        }
        return hfService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }

}
