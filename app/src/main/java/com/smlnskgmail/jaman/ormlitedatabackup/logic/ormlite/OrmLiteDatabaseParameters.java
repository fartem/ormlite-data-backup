package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite;

import android.content.Context;

import androidx.annotation.NonNull;

public class OrmLiteDatabaseParameters {

    private final Context context;
    private final String databaseName;

    OrmLiteDatabaseParameters(
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

    public String databaseFolder() {
        return context.getDatabasePath(databaseName).getParent();
    }

}
