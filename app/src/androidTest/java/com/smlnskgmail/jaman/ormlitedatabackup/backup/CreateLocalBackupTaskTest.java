package com.smlnskgmail.jaman.ormlitedatabackup.backup;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.test.platform.app.InstrumentationRegistry;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.LocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.tasks.CreateLocalBackupTask;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertTrue;

public class CreateLocalBackupTaskTest {

    private final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void deleteOldBackupOfExists() {
        if (!hasStorageAccessPermission()) {
            throw new RuntimeException("Need granted storage access permission in Settings!");
        }

        File localBackup = localBackupFile();
        if (localBackup.exists()) {
            assertTrue(localBackup.delete());
        }
    }

    private boolean hasStorageAccessPermission() {
        int storagePermissionStatus = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
        return storagePermissionStatus == PackageManager.PERMISSION_GRANTED;
    }

    @Test
    public void createLocalBackup() throws InterruptedException {
        CreateLocalBackupTask createLocalBackupTask = new CreateLocalBackupTask(
                context,
                result -> countDownLatch.countDown()
        );
        createLocalBackupTask.execute();
        countDownLatch.await();

        File localBackup = localBackupFile();
        assertTrue(localBackup.exists());
    }

    private File localBackupFile() {
        LocalBackupPath localBackupPath = new LocalBackupPath(HelperFactory.instance().databaseParameters());
        return new File(localBackupPath.pathAsString());
    }

}