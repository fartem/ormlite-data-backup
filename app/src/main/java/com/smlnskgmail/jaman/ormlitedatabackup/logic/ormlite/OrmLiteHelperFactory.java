package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;

@SuppressLint("StaticFieldLeak")
public class OrmLiteHelperFactory {

    private static OrmLiteDatabaseHelper ORMLiteDatabaseHelper;

    private static OrmLiteDatabaseParameters ORMLiteDatabaseParameters;

    private OrmLiteHelperFactory(@NonNull OrmLiteDatabaseHelper ormLiteDatabaseHelper) {
        this.ORMLiteDatabaseHelper = ormLiteDatabaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (ORMLiteDatabaseHelper != null) {
            releaseHelper();
        }
        ORMLiteDatabaseHelper = OpenHelperManager.getHelper(context, OrmLiteDatabaseHelper.class);
    }

    public static OrmLiteDatabaseHelper databaseHelper() {
        return ORMLiteDatabaseHelper;
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        ORMLiteDatabaseHelper = null;
    }

    public static OrmLiteDatabaseParameters databaseParameters() {
        if (ORMLiteDatabaseParameters == null) {
            String databaseName = ORMLiteDatabaseHelper.databaseName();
            ORMLiteDatabaseParameters = new OrmLiteDatabaseParameters(
                    ORMLiteDatabaseHelper.context(),
                    databaseName
            );
        }
        return ORMLiteDatabaseParameters;
    }

}
