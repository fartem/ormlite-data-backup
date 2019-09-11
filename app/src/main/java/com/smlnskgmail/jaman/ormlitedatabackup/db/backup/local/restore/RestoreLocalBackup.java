package com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.restore;

import android.os.AsyncTask;

public class RestoreLocalBackup extends AsyncTask<Void, Void, Boolean> {

    private RestoreLocalBackupTarget restoreLocalBackupTarget;

    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        restoreLocalBackupTarget.localBackupRestored(result);
    }

}
