package com.smlnskgmail.jaman.ormlitedatabackup;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;

public class App extends android.app.Application {

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
