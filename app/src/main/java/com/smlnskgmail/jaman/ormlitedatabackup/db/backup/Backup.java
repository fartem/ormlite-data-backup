package com.smlnskgmail.jaman.ormlitedatabackup.db.backup;

import android.content.Context;

import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.CreateLocalBackup;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.RestoreLocalBackup;

public class Backup {

    private Context context;

    public Backup(Context context) {
        this.context = context;
    }

    public void createLocalBackup() {
        new CreateLocalBackup(context).execute();
    }

    public void restoreLocalBackup() {
        new RestoreLocalBackup().execute();
    }

}
