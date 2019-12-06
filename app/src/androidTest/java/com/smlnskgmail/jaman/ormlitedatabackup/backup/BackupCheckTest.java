package com.smlnskgmail.jaman.ormlitedatabackup.backup;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.BackupCheck;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.FileCopy;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class BackupCheckTest {

    private final Context context = InstrumentationRegistry.getInstrumentation().getContext();
    private final Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private DatabaseParameters databaseParameters;

    @Before
    public void init() {
        databaseParameters = HelperFactory.instance().databaseParameters();
    }

    @Test
    public void checkValidDB() throws IOException {
        copyBackupFileToAppFolder(true);
        tryRestoreBackup(true);
    }

    @Test
    public void checkInvalidDB() throws IOException {
        copyBackupFileToAppFolder(false);
        tryRestoreBackup(false);
    }

    private void copyBackupFileToAppFolder(boolean isValid) throws IOException {
        String backupFolder = isValid ? "valid" : "invalid";
        InputStream backupFile = context.getResources().getAssets().open(
                "backups/" + backupFolder + "/ormlite.backup"
        );

        new FileCopy(
                targetContext,
                backupFile,
                databaseParameters.databasePath()
        ).copy();
    }

    private void tryRestoreBackup(boolean isValid) {
        BackupCheck backupCheck = new BackupCheck(
                targetContext,
                databaseParameters.databasePath()
        );

        assertEquals(
                isValid,
                backupCheck.isValidDB()
        );
    }

}
