package com.example.metaldude.sportflex;

import static com.example.metaldude.sportflex.utils.Utils.BASE_URL;

import android.app.Application;
import com.example.metaldude.sportflex.di.component.ApiComponent;
import com.example.metaldude.sportflex.di.component.DaggerApiComponent;
import com.example.metaldude.sportflex.di.modules.ApiModule;
import com.example.metaldude.sportflex.di.modules.AppModule;


public class App extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
            .appModule(new AppModule(this))
            .apiModule(new ApiModule("https://simplifiedcoding.net/demos/"))
            .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}