package com.smlnskgmail.jaman.ormlitedatabackup.entities.entities;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventTest extends BaseEntityTest {

    private int id = 1;
    private String title = "Title";
    private String subtitle = "Subtitle";
    private Date date = new Date();

    private Event event = new Event(
            id,
            title,
            subtitle,
            date
    );

    @Override
    public void validateFields() {
        assertEquals(
                title,
                event.title()
        );
        assertEquals(
                subtitle,
                event.subtitle()
        );
        assertEquals(
                date,
                event.date()
        );
    }

    @Override
    public void validateEquals() {
        assertEquals(
                event,
                event
        );
        assertEquals(
                new Event(
                        id,
                        title,
                        subtitle,
                        date
                ),
                event
        );

        assertNotEquals(
                new Event(
                        id,
                        "Another title",
                        subtitle,
                        date
                ),
                event
        );
        assertNotEquals(
                new Event(
                        id,
                        title,
                        "Another subtitle",
                        date
                ),
                event
        );

        Date anotherDate = new Date(
                Calendar.getInstance().getTimeInMillis() - 1_000
        );
        assertNotEquals(
                new Event(
                        id,
                        title,
                        subtitle,
                        anotherDate
                ),
                event
        );
        assertNotEquals(
                event,
                null
        );
        assertNotEquals(
                event,
                "String"
        );
    }

    @Override
    public void validateHashCode() {
        assertEquals(
                event.hashCode(),
                event.hashCode()
        );
        assertEquals(
                new Event(
                        id,
                        title,
                        subtitle,
                        date
                ).hashCode(),
                event.hashCode()
        );

        assertNotEquals(
                new Event(
                        id,
                        "Another title",
                        subtitle,
                        date
                ).hashCode(),
                event.hashCode()
        );
    }

}
