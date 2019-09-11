package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.create;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.LocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.tools.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

public class CreateLocalBackup extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final CreateLocalBackupTarget createLocalBackupTarget;
    private final Log log;

    public CreateLocalBackup(Context context, CreateLocalBackupTarget createLocalBackupTarget, Log log) {
        this.context = context;
        this.createLocalBackupTarget = createLocalBackupTarget;
        this.log = log;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        checkpoint();
        DatabaseParameters databaseParameters = HelperFactory.instance().databaseParameters();
        String from = databaseParameters.databasePath();
        String to = new LocalBackupPath(databaseParameters).pathAsString();
        return new FileCopy(context, from, to, new ErrorLog()).copy();
    }

    private void checkpoint() {
        try {
            HelperFactory.instance().execSQL("PRAGMA wal_checkpoint");
        } catch (SQLiteException e) {
            if (log != null) {
                log.log(e);
            }
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        createLocalBackupTarget.localBackupCreated(result);
    }

}
