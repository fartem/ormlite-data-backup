package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.tasks.create;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.OrmLiteLocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.support.L;

public class CreateOrmLiteLocalBackupTask extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final CreateLocalBackupTarget backupTarget;

    public CreateOrmLiteLocalBackupTask(
            @NonNull Context context,
            @NonNull CreateLocalBackupTarget backupTarget
    ) {
        this.context = context;
        this.backupTarget = backupTarget;
    }

    @NonNull
    @Override
    protected Boolean doInBackground(@NonNull Void... voids) {
        checkpoint();
        OrmLiteDatabaseParameters parameters
                = OrmLiteHelperFactory.databaseParameters();
        String from = parameters.databasePath();
        String to = new OrmLiteLocalBackupPath(parameters).pathAsString();
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
            L.e(e);
        }
    }

    @Override
    protected void onPostExecute(@NonNull Boolean result) {
        backupTarget.localBackupCreated(result);
    }

}
