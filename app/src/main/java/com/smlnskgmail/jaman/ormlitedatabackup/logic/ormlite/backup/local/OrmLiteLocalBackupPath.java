package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.local;

import android.os.Environment;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;

public class OrmLiteLocalBackupPath {

    private final OrmLiteDatabaseParameters ormLiteDatabaseParameters;

    public OrmLiteLocalBackupPath(@NonNull OrmLiteDatabaseParameters ormLiteDatabaseParameters) {
        this.ormLiteDatabaseParameters = ormLiteDatabaseParameters;
    }

    public String pathAsString() {
        String databaseName = ormLiteDatabaseParameters.databaseName();
        String backupFileName = databaseName.substring(0, databaseName.indexOf(".")) + ".backup";
        return Environment.getExternalStorageDirectory() + "/OrmLiteDataBackup/" + backupFileName;
    }

}
