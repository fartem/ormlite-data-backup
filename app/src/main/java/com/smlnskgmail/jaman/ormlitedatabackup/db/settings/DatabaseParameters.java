package com.smlnskgmail.jaman.ormlitedatabackup.db.settings;

import android.content.Context;

public class DatabaseParameters {

    private final Context context;
    private final String databaseName;

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
