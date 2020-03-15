package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup;

import android.os.Environment;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteDatabaseParameters;

public class OrmLiteLocalBackupPath {

    private final OrmLiteDatabaseParameters parameters;

    public OrmLiteLocalBackupPath(@NonNull OrmLiteDatabaseParameters parameters) {
        this.parameters = parameters;
    }

    public String pathAsString() {
        String databaseName = parameters.databaseName();
        String backupFileName = databaseName.substring(
                0,
                databaseName.indexOf(".")
        ) + ".backup";
        return Environment.getExternalStorageDirectory()
                + "/OrmLiteDataBackup/"
                + backupFileName;
    }

}
