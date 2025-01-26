package com.example.featuretogglelibrary;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoader.init(this);
    }

}
