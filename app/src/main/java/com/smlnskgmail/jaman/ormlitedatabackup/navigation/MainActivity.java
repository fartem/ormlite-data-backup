package com.smlnskgmail.jaman.ormlitedatabackup.navigation;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.db.backup.Backup;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.EventFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;
import com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist.EventsAdapter;

import java.sql.SQLException;
import java.util.List;

import jahirfiquitiva.libs.fabsmenu.TitleFAB;

public class MainActivity extends AppCompatActivity {

    private final Log errorLog = new ErrorLog();

    private RecyclerView eventsList;

    private TitleFAB createLocalBackup;
    private TitleFAB restoreLocalBackup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadEvents();
    }

    private void initViews() {
        eventsList = findViewById(R.id.events_list);

        findViewById(R.id.create_local_backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Backup(MainActivity.this).createLocalBackup();
            }
        });
        findViewById(R.id.restore_local_backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Backup(MainActivity.this).restoreLocalBackup();
            }
        });
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
