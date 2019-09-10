package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local;

import android.os.Environment;

import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;

class LocalBackupPath {

    private final DatabaseParameters databaseParameters;

    LocalBackupPath(DatabaseParameters databaseParameters) {
        this.databaseParameters = databaseParameters;
    }

    String pathAsString() {
        return Environment.getExternalStorageDirectory() + "/OrmLiteDataBackup/" + databaseParameters.databasePath();
    }

}
