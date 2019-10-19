package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local;

import android.os.Environment;

import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.DatabaseParameters;

public class LocalBackupPath {

    private final DatabaseParameters databaseParameters;

    public LocalBackupPath(DatabaseParameters databaseParameters) {
        this.databaseParameters = databaseParameters;
    }

    public String pathAsString() {
        String databaseName = databaseParameters.databaseName();
        String backupFileName = databaseName.substring(0, databaseName.indexOf(".")) + ".backup";
        return Environment.getExternalStorageDirectory() + "/OrmLiteDataBackup/" + backupFileName;
    }

}
