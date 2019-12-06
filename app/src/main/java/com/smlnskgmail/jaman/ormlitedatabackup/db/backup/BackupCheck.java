package com.smlnskgmail.jaman.ormlitedatabackup.db.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.smlnskgmail.jaman.ormlitedatabackup.db.DatabaseHelper;
import com.smlnskgmail.jaman.ormlitedatabackup.tools.L;

public class BackupCheck {

    private final Context context;
    private final String databasePath;

    public BackupCheck(
            @NonNull Context context,
            @NonNull String databasePath
    ) {
        this.context = context;
        this.databasePath = databasePath;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public boolean isValidDB() {
        try (SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(
                databasePath,
                null,
                SQLiteDatabase.OPEN_READONLY
        ); OrmLiteSqliteOpenHelper openHelper = new DatabaseHelper(context, databasePath)) {
            int version = sqLiteDatabase.getVersion();
            for (Class clazz : DatabaseHelper.DB_CLASSED) {
                Dao dao = openHelper.getDao(clazz);
                dao.countOf();
            }
            return true;
        } catch (Exception e) {
            L.e(e);
            return false;
        }
    }

}
