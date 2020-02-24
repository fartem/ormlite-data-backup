package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;

@SuppressLint("StaticFieldLeak")
public class OrmLiteHelperFactory {

    private static OrmLiteDatabaseHelper ormLiteDatabaseHelper;

    private static OrmLiteDatabaseParameters ormLiteDatabaseParameters;

    private OrmLiteHelperFactory(
            @NonNull OrmLiteDatabaseHelper ormLiteDatabaseHelper
    ) {
        OrmLiteHelperFactory.ormLiteDatabaseHelper = ormLiteDatabaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (ormLiteDatabaseHelper != null) {
            releaseHelper();
        }
        ormLiteDatabaseHelper = OpenHelperManager.getHelper(
                context,
                OrmLiteDatabaseHelper.class
        );
    }

    public static OrmLiteDatabaseHelper databaseHelper() {
        return ormLiteDatabaseHelper;
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        ormLiteDatabaseHelper = null;
    }

    public static OrmLiteDatabaseParameters databaseParameters() {
        if (ormLiteDatabaseParameters == null) {
            String databaseName = ormLiteDatabaseHelper.databaseName();
            ormLiteDatabaseParameters = new OrmLiteDatabaseParameters(
                    ormLiteDatabaseHelper.context(),
                    databaseName
            );
        }
        return ormLiteDatabaseParameters;
    }

}
