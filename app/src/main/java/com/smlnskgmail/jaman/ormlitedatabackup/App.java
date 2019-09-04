package com.smlnskgmail.jaman.ormlitedatabackup;

import android.app.Application;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        HelperFactory.releaseHelper();
    }

}
