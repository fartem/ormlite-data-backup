package com.smlnskgmail.jaman.ormlitedatabackup.navigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.db.data.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.db.data.event.EventFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.ErrorLog;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;
import com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist.EventsAdapter;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Log errorLog = new ErrorLog();

    private RecyclerView eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventsList = findViewById(R.id.events_list);

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
