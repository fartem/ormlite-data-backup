package com.smlnskgmail.jaman.ormlitedatabackup;

import com.smlnskgmail.jaman.ormlitedatabackup.backup.CreateOrmLiteLocalBackupTaskTest;
import com.smlnskgmail.jaman.ormlitedatabackup.backup.OrmLiteBackupCheckTest;
import com.smlnskgmail.jaman.ormlitedatabackup.ui.CreateEventTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateEventTest.class,
        CreateOrmLiteLocalBackupTaskTest.class,
        OrmLiteBackupCheckTest.class
})
public class AndroidTestSuite { }
