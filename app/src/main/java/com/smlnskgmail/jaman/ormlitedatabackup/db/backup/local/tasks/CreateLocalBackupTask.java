package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.LocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.tools.L;

public class CreateLocalBackupTask extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final CreateLocalBackupTarget createLocalBackupTarget;

    public CreateLocalBackupTask(
            @NonNull Context context,
            @NonNull CreateLocalBackupTarget createLocalBackupTarget
    ) {
        this.context = context;
        this.createLocalBackupTarget = createLocalBackupTarget;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        checkpoint();
        DatabaseParameters databaseParameters = HelperFactory.instance().databaseParameters();
        String from = databaseParameters.databasePath();
        String to = new LocalBackupPath(databaseParameters).pathAsString();
        return new FileCopy(
                context,
                from,
                to
        ).copy();
    }

    private void checkpoint() {
        try {
            HelperFactory.instance().execSQL("PRAGMA wal_checkpoint");
        } catch (SQLiteException e) {
            L.e(e);
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
