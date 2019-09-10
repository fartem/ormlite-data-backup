package com.smlnskgmail.jaman.ormlitedatabackup.db;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.smlnskgmail.jaman.ormlitedatabackup.db.entities.EntityWithId;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.InfoLog;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class HelperFactory {

    private static HelperFactory helperFactory;

    @SuppressLint("StaticFieldLeak")
    private final DatabaseHelper databaseHelper;

    private HelperFactory(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (helperFactory != null) {
            releaseHelper();
        }
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        databaseHelper.enableLogs(new InfoLog());
        helperFactory = new HelperFactory(databaseHelper);
    }

    public static HelperFactory instance() {
        return helperFactory;
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        helperFactory = null;
    }

    public<T extends EntityWithId> List<T> allOf(Class<T> clazz) throws SQLException {
        return databaseHelper.getDao(clazz).queryForAll();
    }

    @SafeVarargs
    public final <T extends EntityWithId> void saveAll(Class<T> clazz, T... entities) throws SQLException {
        databaseHelper.getDao(clazz).create(Arrays.asList(entities));
    }

    public<T extends EntityWithId> void cleanAll(Class<T> clazz) throws SQLException {
        databaseHelper.getDao(clazz).deleteBuilder().delete();
    }

}
