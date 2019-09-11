package com.smlnskgmail.jaman.ormlitedatabackup.db.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.DatabaseHelper;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

public class BackupCheck {

    private Context context;
    private String databaseName;
    private Log log;

    public BackupCheck(Context context, String databaseName, Log log) {
        this.context = context;
        this.databaseName = databaseName;
        this.log = log;
    }

    public boolean isValidDB() {
        SQLiteDatabase sqLiteDatabase = null;
        OrmLiteSqliteOpenHelper openHelper = null;;

        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase(databaseName, null, SQLiteDatabase.OPEN_READONLY);
            openHelper = new DatabaseHelper(context, databaseName);
            // Needed to checking the file extension and access
            int version = sqLiteDatabase.getVersion();
            for (Class clazz: DatabaseHelper.DB_CLASSED) {
                Dao dao = HelperFactory.instance().daoOf(clazz); // Keep
            }
            return true;
        } catch (Exception e) {
            if (log != null) {
                log.log(e);
            }
            return false;
        } finally {
            sqLiteDatabase.close();
            openHelper.close();
        }
    }

}
