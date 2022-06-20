package com.example.hunter1;

import android.app.Application;

import com.example.hunter1.utils.LocationProvider;
import com.example.hunter1.utils.MSP;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MSP.initHelper(this);
        LocationProvider.initHelper(this);
//        MySignal.initHelper(this);

    }
}
