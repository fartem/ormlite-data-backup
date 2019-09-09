package com.smlnskgmail.jaman.ormlitedatabackup.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.data.DefaultData;
import com.smlnskgmail.jaman.ormlitedatabackup.data.Event;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ormlite.db";

    private static final int DATABASE_VERSION_1 = 1;

    private static final int CURRENT_DATABASE_VERSION = DATABASE_VERSION_1;

    private static final Class[] DB_CLASSED = new Class[]{
            Event.class
    };

    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            createTables(connectionSource);
            new DefaultData(context, this).create();
        } catch (SQLException e) {

        }
    }

    private void createTables(ConnectionSource connectionSource) throws SQLException {
        for (Class clazz: DB_CLASSED) {
            TableUtils.createTable(connectionSource, clazz);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {}

}
