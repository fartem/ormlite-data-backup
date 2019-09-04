package com.smlnskgmail.jaman.ormlitedatabackup.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class HelperFactory {

    private static DatabaseHelper databaseHelper;

    @Nullable
    public static DatabaseHelper getHelper() {
        return databaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (databaseHelper != null) {
            releaseHelper();
        }
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }

}
