package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.tools.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;

public class CreateLocalBackup extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private Context context;

    public CreateLocalBackup(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        HelperFactory.instance().execSQL("PRAGMA wal_checkpoint");
        DatabaseParameters databaseParameters = HelperFactory.instance().databaseSettings();
        String from = databaseParameters.databasePath();
        String to = new LocalBackupPath(databaseParameters).pathAsString();
        return new FileCopy(context, from, to, new ErrorLog()).copy();
    }

}
