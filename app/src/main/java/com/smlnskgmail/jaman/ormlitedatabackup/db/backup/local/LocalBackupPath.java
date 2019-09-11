package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local;

import android.os.Environment;

import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;

public class LocalBackupPath {

    private final DatabaseParameters databaseParameters;

    public LocalBackupPath(DatabaseParameters databaseParameters) {
        this.databaseParameters = databaseParameters;
    }

    public String pathAsString() {
        return Environment.getExternalStorageDirectory() + "/OrmLiteDataBackup/" + databaseParameters.databaseName();
    }

}
