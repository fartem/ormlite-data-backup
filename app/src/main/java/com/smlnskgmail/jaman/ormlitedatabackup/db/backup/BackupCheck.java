package com.smlnskgmail.jaman.ormlitedatabackup.db.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.DatabaseHelper;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

public class BackupCheck {

    private final Context context;
    private final String databaseName;
    private final Log log;

    public BackupCheck(Context context, String databaseName, Log log) {
        this.context = context;
        this.databaseName = databaseName;
        this.log = log;
    }

    public boolean isValidDB() {
        try (SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(databaseName, null, SQLiteDatabase.OPEN_READONLY);
             OrmLiteSqliteOpenHelper openHelper = new DatabaseHelper(context, databaseName)) {
            int version = sqLiteDatabase.getVersion();
            log.message("DB version: " + version);
            for (Class clazz : DatabaseHelper.DB_CLASSED) {
                Dao dao = openHelper.getDao(clazz);
                log.message("Table: " + dao.getTableName());
            }
            return true;
        } catch (Exception e) {
            if (log != null) {
                log.log(e);
            }
            return false;
        }
    }

}
