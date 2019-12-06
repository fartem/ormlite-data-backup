package com.smlnskgmail.jaman.ormlitedatabackup.db.backup;

import android.content.Context;

import androidx.annotation.NonNull;

public class DatabaseParameters {

    private final Context context;
    private final String databaseName;

    public DatabaseParameters(
            @NonNull Context context,
            @NonNull String databaseName
    ) {
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
