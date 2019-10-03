package com.smlnskgmail.jaman.ormlitedatabackup.features.backup;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.BackupCheck;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.tools.FileCopy;
import com.smlnskgmail.jaman.ormlitedatabackup.db.settings.DatabaseParameters;
import com.smlnskgmail.jaman.ormlitedatabackup.db.structure.HelperFactory;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;

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
        tryRestoreBackup();
    }

    @Test
    public void checkInvalidDB() throws IOException {
        copyBackupFileToAppFolder(false);
        tryRestoreBackup();
    }

    private void copyBackupFileToAppFolder(boolean isValid) throws IOException {
        String backupFolder = isValid ? "valid" : "invalid";
        InputStream backupFile = context.getResources().getAssets().open("backups/" + backupFolder + "/ormlite.db");
        new FileCopy(context, backupFile, databaseParameters.databasePath(), null).copy();
    }

    private void tryRestoreBackup() {
        BackupCheck backupCheck = new BackupCheck(targetContext, databaseParameters.databasePath(), null);

        assertFalse(backupCheck.isValidDB());
    }

}
