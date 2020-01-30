package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.OrmLiteBackupCheck;

import java.io.File;

public class RestoreOrmLiteLocalBackupTask extends AsyncTask<Void, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final RestoreLocalBackupTarget restoreLocalBackupTarget;

    private final String backupPath;

    public RestoreOrmLiteLocalBackupTask(
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
        OrmLiteDatabaseParameters ormLiteDatabaseParameters = OrmLiteHelperFactory.databaseParameters();
        String databasePath = ormLiteDatabaseParameters.databasePath();

        boolean success = new FileCopy(
                context,
                backupPath,
                databasePath
        ).copy();
        if (success) {
            boolean isValidDB = new OrmLiteBackupCheck(
                    context,
                    databasePath
            ).isValidDB();
            if (isValidDB) {
                if (context.deleteDatabase(ormLiteDatabaseParameters.databaseName())) {
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
