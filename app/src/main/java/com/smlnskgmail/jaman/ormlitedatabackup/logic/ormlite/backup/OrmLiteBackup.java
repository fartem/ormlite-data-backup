package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.local.OrmLiteLocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.local.tasks.CreateOrmLiteLocalBackupTask;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.local.tasks.RestoreOrmLiteLocalBackupTask;

public class OrmLiteBackup {

    private final Context context;

    private CreateOrmLiteLocalBackupTask.CreateLocalBackupTarget createLocalBackupTarget;
    private RestoreOrmLiteLocalBackupTask.RestoreLocalBackupTarget restoreLocalBackupTarget;

    public OrmLiteBackup(
            @NonNull Context context,
            @NonNull CreateOrmLiteLocalBackupTask.CreateLocalBackupTarget createLocalBackupTarget
    ) {
        this.context = context;
        this.createLocalBackupTarget = createLocalBackupTarget;
    }

    public OrmLiteBackup(
            @NonNull Context context,
            @NonNull RestoreOrmLiteLocalBackupTask.RestoreLocalBackupTarget restoreLocalBackupTarget
    ) {
        this.context = context;
        this.restoreLocalBackupTarget = restoreLocalBackupTarget;
    }

    public void createLocalBackup() {
        new CreateOrmLiteLocalBackupTask(
                context,
                createLocalBackupTarget
        ).execute();
    }

    public void restoreLocalBackup() {
        OrmLiteDatabaseParameters ormLiteDatabaseParameters = OrmLiteHelperFactory.databaseParameters();
        String backupPath = new OrmLiteLocalBackupPath(ormLiteDatabaseParameters).pathAsString();

        new RestoreOrmLiteLocalBackupTask(
                context,
                restoreLocalBackupTarget,
                backupPath
        ).execute();
    }

}
