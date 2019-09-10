package com.smlnskgmail.jaman.ormlitedatabackup.db.data;

import android.content.Context;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.db.data.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.db.data.event.EventFactory;

import java.sql.SQLException;
import java.util.Calendar;

public class DefaultData {

    private final Context context;

    public DefaultData(Context context) {
        this.context = context;
    }

    public void create() throws SQLException {
        Event firstEvent = new Event(context.getString(R.string.first_event_title),
                context.getString(R.string.first_event_subtitle), Calendar.getInstance().getTime());
        EventFactory.saveAll(firstEvent);
    }

}
