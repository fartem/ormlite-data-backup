package com.smlnskgmail.jaman.ormlitedatabackup;

import com.smlnskgmail.jaman.ormlitedatabackup.backup.BackupCheckTest;
import com.smlnskgmail.jaman.ormlitedatabackup.backup.CreateLocalBackupTaskTest;
import com.smlnskgmail.jaman.ormlitedatabackup.ui.CreateEventTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateEventTest.class,
        CreateLocalBackupTaskTest.class,
        BackupCheckTest.class
})
public class AndroidTestSuite {

}
