package com.smlnskgmail.jaman.ormlitedatabackup;

import com.smlnskgmail.jaman.ormlitedatabackup.backup.OrmLiteBackupCheckTest;
import com.smlnskgmail.jaman.ormlitedatabackup.ui.CreateEventTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateEventTest.class,
        OrmLiteBackupCheckTest.class
})
public class TravisCIAndroidTestSuite {

}