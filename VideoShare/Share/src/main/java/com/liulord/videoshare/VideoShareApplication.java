package com.liulord.videoshare;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by aaronliu on 14-12-2.
 */
public class VideoShareApplication extends Application {

    static VideoShareApplication sInstance;

    public VideoShareApplication() {
        super();
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }


    public static VideoShareApplication getInstance()
    {
        return sInstance;
    }

}
