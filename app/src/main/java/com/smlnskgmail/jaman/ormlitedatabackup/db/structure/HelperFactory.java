package com.smlnskgmail.jaman.ormlitedatabackup.db.structure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.smlnskgmail.jaman.ormlitedatabackup.db.entities.EntityWithId;
import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class HelperFactory {

    private static HelperFactory helperFactory;

    private final DatabaseHelper databaseHelper;

    private DatabaseParameters databaseParameters;

    private HelperFactory(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public static void setHelper(@NonNull Context context) {
        if (helperFactory != null) {
            releaseHelper();
        }
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        databaseHelper.enableLogs(new ErrorLog());
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

    public final <T extends EntityWithId> void save(Class<T> clazz, T entity) throws SQLException {
        databaseHelper.getDao(clazz).create(entity);
    }

    public<T extends EntityWithId> void cleanAll(Class<T> clazz) throws SQLException {
        databaseHelper.getDao(clazz).deleteBuilder().delete();
    }

    public void execSQL(String sql) throws SQLiteException {
        databaseHelper.getWritableDatabase().execSQL(sql);
    }

    public DatabaseParameters databaseSettings() {
        if (databaseParameters == null) {
            String databaseName = databaseHelper.databaseName();
            databaseParameters = new DatabaseParameters(databaseHelper.context(), databaseName);
        }
        return databaseParameters;
    }

}
