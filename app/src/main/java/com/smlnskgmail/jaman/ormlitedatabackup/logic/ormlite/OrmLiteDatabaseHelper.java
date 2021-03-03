package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.support.L;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final Class[] DB_CLASSED = new Class[]{
            Event.class
    };

    private static final String DATABASE_NAME = "ormlite.db";

    private static final int DATABASE_VERSION_1 = 1;

    private static final int DATABASE_VERSION = DATABASE_VERSION_1;

    private final Context context;

    @SuppressWarnings("unused")
    public OrmLiteDatabaseHelper(@NonNull Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION,
                R.raw.ormlite_config
        );
        this.context = context;
    }

    public OrmLiteDatabaseHelper(
            @NonNull Context context,
            @NonNull String databaseName
    ) {
        super(
                context,
                databaseName,
                null,
                DATABASE_VERSION,
                R.raw.ormlite_config
        );
        this.context = context;
    }

    @Override
    public void onCreate(
            @NonNull SQLiteDatabase database,
            @NonNull ConnectionSource connectionSource
    ) {
        try {
            createTables(connectionSource);
        } catch (SQLException e) {
            L.e(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void createTables(
            @NonNull ConnectionSource connectionSource
    ) throws SQLException {
        for (Class clazz: DB_CLASSED) {
            TableUtils.createTable(connectionSource, clazz);
        }
    }

    @Override
    public void onUpgrade(
            @NonNull SQLiteDatabase database,
            @NonNull ConnectionSource connectionSource,
            int oldVersion,
            int newVersion
    ) {

    }

    @NonNull
    String databaseName() {
        return DATABASE_NAME;
    }

    @NonNull
    Context context() {
        return context;
    }

    @NonNull
    public List<Event> allEvents() {
        try {
            return getDao(Event.class).queryForAll();
        } catch (SQLException e) {
            L.e(e);
        }
        return Collections.emptyList();
    }

    public void saveEvent(@NonNull Event event) {
        try {
            getDao(Event.class).create(event);
        } catch (SQLException e) {
            L.e(e);
        }
    }

    public void deleteEvent(@NonNull Event event) {
        try {
            getDao(Event.class).delete(event);
        } catch (SQLException e) {
            L.e(e);
        }
    }

    public long countOfEvents() {
        try {
            return getDao(Event.class).countOf();
        } catch (SQLException e) {
            L.e(e);
        }
        return 0;
    }

    public void execSQL(
            @NonNull String sql
    ) throws SQLiteException {
        getWritableDatabase().execSQL(sql);
    }

}
