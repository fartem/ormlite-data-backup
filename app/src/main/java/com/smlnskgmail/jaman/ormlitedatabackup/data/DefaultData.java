package com.smlnskgmail.jaman.ormlitedatabackup.data;

import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.smlnskgmail.jaman.ormlitedatabackup.R;

import java.sql.SQLException;
import java.util.Calendar;

public class DefaultData {

    private final Context context;
    private final OrmLiteSqliteOpenHelper helper;

    public DefaultData(Context context, OrmLiteSqliteOpenHelper helper) {
        this.context = context;
        this.helper = helper;
    }

    public void create() throws SQLException {
        Event firstEvent = new Event(context.getString(R.string.first_event_title),
                context.getString(R.string.first_event_subtitle), Calendar.getInstance().getTime());
        helper.getDao(Event.class).create(firstEvent);
    }

}
