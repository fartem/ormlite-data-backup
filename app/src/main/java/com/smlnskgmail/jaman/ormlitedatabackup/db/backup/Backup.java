package com.smlnskgmail.jaman.ormlitedatabackup.db.backup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.LocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.tasks.CreateLocalBackupTask;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.tasks.RestoreLocalBackupTask;

public class Backup {

    private final Context context;

    private CreateLocalBackupTask.CreateLocalBackupTarget createLocalBackupTarget;
    private RestoreLocalBackupTask.RestoreLocalBackupTarget restoreLocalBackupTarget;

    public Backup(
            @NonNull Context context,
            @NonNull CreateLocalBackupTask.CreateLocalBackupTarget createLocalBackupTarget
    ) {
        this.context = context;
        this.createLocalBackupTarget = createLocalBackupTarget;
    }

    public Backup(
            @NonNull Context context,
            @NonNull RestoreLocalBackupTask.RestoreLocalBackupTarget restoreLocalBackupTarget
    ) {
        this.context = context;
        this.restoreLocalBackupTarget = restoreLocalBackupTarget;
    }

    public void createLocalBackup() {
        new CreateLocalBackupTask(
                context,
                createLocalBackupTarget
        ).execute();
    }

    public void restoreLocalBackup() {
        DatabaseParameters databaseParameters = HelperFactory.instance().databaseParameters();
        String backupPath = new LocalBackupPath(databaseParameters).pathAsString();

        new RestoreLocalBackupTask(
                context,
                restoreLocalBackupTarget,
                backupPath
        ).execute();
    }

}
