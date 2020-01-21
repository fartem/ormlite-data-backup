package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.OrmLiteLocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.tools.LogTool;

public class CreateOrmLiteLocalBackupTask extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final CreateLocalBackupTarget createLocalBackupTarget;

    public CreateOrmLiteLocalBackupTask(
            @NonNull Context context,
            @NonNull CreateLocalBackupTarget createLocalBackupTarget
    ) {
        this.context = context;
        this.createLocalBackupTarget = createLocalBackupTarget;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        checkpoint();
        OrmLiteDatabaseParameters ormLiteDatabaseParameters = OrmLiteHelperFactory.databaseParameters();
        String from = ormLiteDatabaseParameters.databasePath();
        String to = new OrmLiteLocalBackupPath(ormLiteDatabaseParameters).pathAsString();
        return new FileCopy(
                context,
                from,
                to
        ).copy();
    }

    private void checkpoint() {
        try {
            OrmLiteHelperFactory.databaseHelper().execSQL("PRAGMA wal_checkpoint");
        } catch (SQLiteException e) {
            LogTool.e(e);
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        createLocalBackupTarget.localBackupCreated(result);
    }

    public interface CreateLocalBackupTarget {

        void localBackupCreated(boolean result);

    }

}
