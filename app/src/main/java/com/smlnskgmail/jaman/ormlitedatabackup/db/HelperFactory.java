package com.smlnskgmail.jaman.ormlitedatabackup.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.DatabaseParameters;

@SuppressLint("StaticFieldLeak")
public class HelperFactory {

    private static HelperFactory helperFactory;

    private final DatabaseHelper databaseHelper;

    private DatabaseParameters databaseParameters;

    private HelperFactory(@NonNull DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (helperFactory != null) {
            releaseHelper();
        }
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        helperFactory = new HelperFactory(databaseHelper);
    }

    public DatabaseHelper databaseHelper() {
        return databaseHelper;
    }

    public static HelperFactory instance() {
        return helperFactory;
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        helperFactory = null;
    }

    public void execSQL(@NonNull String sql) throws SQLiteException {
        databaseHelper.getWritableDatabase().execSQL(sql);
    }

    public DatabaseParameters databaseParameters() {
        if (databaseParameters == null) {
            String databaseName = databaseHelper.databaseName();
            databaseParameters = new DatabaseParameters(
                    databaseHelper.context(),
                    databaseName
            );
        }
        return databaseParameters;
    }

}
