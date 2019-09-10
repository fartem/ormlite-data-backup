package com.smlnskgmail.jaman.ormlitedatabackup.db.data.event;

import com.smlnskgmail.jaman.ormlitedatabackup.db.HelperFactory;

import java.sql.SQLException;
import java.util.List;

public class EventFactory {

    public static List<Event> allEvents() throws SQLException {
        return HelperFactory.instance().allOf(Event.class);
    }

    public static void saveAll(Event ...events) throws SQLException {
        HelperFactory.instance().saveAll(Event.class, events);
    }

}
