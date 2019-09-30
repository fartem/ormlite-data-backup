package com.smlnskgmail.jaman.ormlitedatabackup.db.structure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.DefaultData;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ormlite.db";

    private static final int DATABASE_VERSION_1 = 1;

    private static final int DATABASE_VERSION = DATABASE_VERSION_1;

    public static final Class[] DB_CLASSED = new Class[]{
            Event.class
    };

    private final Context context;
    private Log log;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    void enableLogs(Log log) {
        this.log = log;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            createTables(connectionSource);
            new DefaultData(context).create();
        } catch (SQLException e) {
            if (log != null) {
                log.log(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void createTables(ConnectionSource connectionSource) throws SQLException {
        for (Class clazz: DB_CLASSED) {
            TableUtils.createTable(connectionSource, clazz);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {}

    String databaseName() {
        return DATABASE_NAME;
    }

    Context context() {
        return context;
    }

}
