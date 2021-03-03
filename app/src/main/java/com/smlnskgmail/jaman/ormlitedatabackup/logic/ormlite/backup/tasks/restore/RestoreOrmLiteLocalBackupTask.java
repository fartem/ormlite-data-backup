package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.tasks.restore;

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
    private final RestoreLocalBackupTarget backupTarget;

    private final String backupPath;

    public RestoreOrmLiteLocalBackupTask(
            @NonNull Context context,
            @NonNull RestoreLocalBackupTarget backupTarget,
            @NonNull String backupPath
    ) {
        this.context = context;
        this.backupTarget = backupTarget;
        this.backupPath = backupPath;
    }

    @NonNull
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    protected Boolean doInBackground(@NonNull Void... voids) {
        OrmLiteDatabaseParameters parameters
                = OrmLiteHelperFactory.databaseParameters();
        String tempDatabase = parameters.databaseFolder() + "/temp.db";
        boolean success = new FileCopy(
                context,
                backupPath,
                tempDatabase
        ).copy();
        if (success) {
            boolean isValidDB = new OrmLiteBackupCheck(
                    context,
                    tempDatabase
            ).isValidDB();
            if (isValidDB) {
                if (context.deleteDatabase(parameters.databaseName())) {
                    File originalDatabase = new File(parameters.databasePath());
                    // https:/d/stackoverflow.com/questions/14651567/java-file-renameto
                    // -does-rename-file-but-returns-false-why
                    new File(tempDatabase).renameTo(originalDatabase);
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
    protected void onPostExecute(@NonNull Boolean result) {
        backupTarget.localBackupRestored(result);
    }

}
