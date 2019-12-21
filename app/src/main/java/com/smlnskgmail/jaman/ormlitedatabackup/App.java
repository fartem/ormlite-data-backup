package com.smlnskgmail.jaman.ormlitedatabackup;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;

public class App extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OrmLiteHelperFactory.setHelper(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        OrmLiteHelperFactory.releaseHelper();
    }

}
