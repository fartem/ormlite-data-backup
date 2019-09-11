package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.restore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.BackupCheck;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.tools.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

import java.io.File;

public class RestoreLocalBackup extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final RestoreLocalBackupTarget restoreLocalBackupTarget;
    private final String backupPath;
    private final Log log;

    public RestoreLocalBackup(Context context, RestoreLocalBackupTarget restoreLocalBackupTarget,
                              String backupPath, Log log) {
        this.context = context;
        this.restoreLocalBackupTarget = restoreLocalBackupTarget;
        this.backupPath = backupPath;
        this.log = log;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        DatabaseParameters databaseParameters = HelperFactory.instance().databaseParameters();
        String databasePath = databaseParameters.databasePath();

        boolean success = new FileCopy(context, backupPath, databaseParameters.databasePath(), log).copy();
        if (success) {
            boolean isValidDB = new BackupCheck(context, databasePath, log).isValidDB();
            if (isValidDB) {
                if (context.deleteDatabase(databaseParameters.databaseName())) {
                    File originalDatabase = new File(databasePath);
                    return new File(databasePath).renameTo(originalDatabase);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        restoreLocalBackupTarget.localBackupRestored(result);
    }

}
