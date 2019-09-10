package com.smlnskgmail.jaman.ormlitedatabackup.db.settings;

import android.content.Context;

public class DatabaseParameters {

    private Context context;
    private String databaseName;

    public DatabaseParameters(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
    }

    public String databaseName() {
        return databaseName;
    }

    public String databasePath() {
        return context.getDatabasePath(databaseName).getPath();
    }

}
