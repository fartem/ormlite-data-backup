package com.smlnskgmail.jaman.ormlitedatabackup.backup;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.test.platform.app.InstrumentationRegistry;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.OrmLiteLocalBackupPath;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.backup.tasks.create.CreateOrmLiteLocalBackupTask;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertTrue;

public class CreateOrmLiteLocalBackupTaskTest {

    private final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void deleteOldBackupOfExists() {
        if (!hasStorageAccessPermission()) {
            throw new RuntimeException(
                    "Need granted storage access permission in Settings!"
            );
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
        CreateOrmLiteLocalBackupTask createOrmLiteLocalBackupTask = new CreateOrmLiteLocalBackupTask(
                context,
                result -> countDownLatch.countDown()
        );
        createOrmLiteLocalBackupTask.execute();
        countDownLatch.await();

        File localBackup = localBackupFile();
        assertTrue(localBackup.exists());
    }

    private File localBackupFile() {
        OrmLiteLocalBackupPath ormLiteLocalBackupPath = new OrmLiteLocalBackupPath(
                OrmLiteHelperFactory.databaseParameters()
        );
        return new File(ormLiteLocalBackupPath.pathAsString());
    }

}
