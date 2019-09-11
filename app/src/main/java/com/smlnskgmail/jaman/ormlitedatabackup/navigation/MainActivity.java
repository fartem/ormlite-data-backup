package com.smlnskgmail.jaman.ormlitedatabackup.navigation;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.components.BaseActivity;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.Backup;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.create.CreateLocalBackupTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.local.restore.RestoreLocalBackupTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.EventFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.newevent.NewEventBottomSheet;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.newevent.NewEventTarget;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;
import com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist.EventsAdapter;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends BaseActivity implements NewEventTarget {

    private final Log errorLog = new ErrorLog();

    private RecyclerView eventsList;

    @Override
    public int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initViews();
        loadEvents();
    }

    private void initViews() {
        eventsList = findViewById(R.id.events_list);

        setClickToFABTitle(R.id.create_event, view -> {
            NewEventBottomSheet newEventBottomSheet = new NewEventBottomSheet();
            newEventBottomSheet.setupNewEventTarget(MainActivity.this);
            newEventBottomSheet.setupLog(new ErrorLog());
            newEventBottomSheet.show(getSupportFragmentManager(), newEventBottomSheet.getClass().getName());
        });
        setClickToFABTitle(R.id.create_local_backup, view -> {
            new Backup(MainActivity.this, (CreateLocalBackupTarget) result -> {

            }).createLocalBackup();
        });
        setClickToFABTitle(R.id.restore_local_backup, view -> {
            new Backup(MainActivity.this, (RestoreLocalBackupTarget) result -> {

            }).restoreLocalBackup();
        });
    }

    private void setClickToFABTitle(int resId, View.OnClickListener clickListener) {
        findViewById(resId).setOnClickListener(clickListener);
    }

    @Override
    public void newEventAdded() {
        loadEvents();
    }

    private void loadEvents() {
        try {
            List<Event> events = EventFactory.allEvents();
            eventsList.setAdapter(new EventsAdapter(events));
        } catch (SQLException e) {
            errorLog.log(e);
        }
    }

}
