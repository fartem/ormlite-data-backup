package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;

@SuppressLint("StaticFieldLeak")
public class OrmLiteHelperFactory {

    private static OrmLiteDatabaseHelper databaseHelper;

    private static OrmLiteDatabaseParameters databaseParameters;

    private OrmLiteHelperFactory(
            @NonNull OrmLiteDatabaseHelper databaseHelper
    ) {
        OrmLiteHelperFactory.databaseHelper = databaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (databaseHelper != null) {
            releaseHelper();
        }
        databaseHelper = OpenHelperManager.getHelper(
                context,
                OrmLiteDatabaseHelper.class
        );
    }

    @NonNull
    public static OrmLiteDatabaseHelper databaseHelper() {
        return databaseHelper;
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }

    @NonNull
    public static OrmLiteDatabaseParameters databaseParameters() {
        if (databaseParameters == null) {
            String databaseName = databaseHelper.databaseName();
            databaseParameters = new OrmLiteDatabaseParameters(
                    databaseHelper.context(),
                    databaseName
            );
        }
        return databaseParameters;
    }

}
