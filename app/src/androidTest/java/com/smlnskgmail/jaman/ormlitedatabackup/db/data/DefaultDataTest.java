package com.smlnskgmail.jaman.ormlitedatabackup.db.data;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.db.BaseDBTest;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.DefaultData;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.EventFactory;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultDataTest extends BaseDBTest {

    private final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void validateDefaultDataCreation() throws SQLException {
        new DefaultData(context).create();

        List<Event> events = EventFactory.allEvents();

        assertEquals(events.size(), 1);

        Event firstEvent = events.get(0);

        String title = context.getString(R.string.first_event_title);
        String subtitle = context.getString(R.string.first_event_subtitle);
        Date date = Calendar.getInstance().getTime();

        assertEquals(firstEvent.title(), title);
        assertEquals(firstEvent.subtitle(), subtitle);
        assertTrue(firstEvent.date().before(date));
    }

}