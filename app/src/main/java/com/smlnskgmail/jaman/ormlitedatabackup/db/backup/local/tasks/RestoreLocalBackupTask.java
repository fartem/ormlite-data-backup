package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.BackupCheck;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.FileCopy;

import java.io.File;

public class RestoreLocalBackupTask extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final RestoreLocalBackupTarget restoreLocalBackupTarget;

    private final String backupPath;

    public RestoreLocalBackupTask(
            @NonNull Context context,
            @NonNull RestoreLocalBackupTarget restoreLocalBackupTarget,
            @NonNull String backupPath
    ) {
        this.context = context;
        this.restoreLocalBackupTarget = restoreLocalBackupTarget;
        this.backupPath = backupPath;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    protected Boolean doInBackground(Void... voids) {
        DatabaseParameters databaseParameters = HelperFactory.instance().databaseParameters();
        String databasePath = databaseParameters.databasePath();

        boolean success = new FileCopy(
                context,
                backupPath,
                databasePath
        ).copy();
        if (success) {
            boolean isValidDB = new BackupCheck(
                    context,
                    databasePath
            ).isValidDB();
            if (isValidDB) {
                if (context.deleteDatabase(databaseParameters.databaseName())) {
                    File originalDatabase = new File(databasePath);
                    // https://stackoverflow.com/questions/14651567/java-file-renameto
                    // -does-rename-file-but-returns-false-why
                    new File(databasePath).renameTo(originalDatabase);
                    return true;
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

    public interface RestoreLocalBackupTarget {

        void localBackupRestored(boolean success);

    }

}
