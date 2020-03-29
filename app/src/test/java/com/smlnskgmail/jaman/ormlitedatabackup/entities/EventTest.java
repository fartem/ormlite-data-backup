package com.smlnskgmail.jaman.ormlitedatabackup.entities;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventTest {

    @Test
    public void equalsTest() {
        int id = 1;
        String title = "Title";
        String subtitle = "Subtitle";
        Date date = new Date();

        Event firstEvent = new Event(
                id,
                title,
                subtitle,
                date
        );
        Event secondEvent= new Event(
                id,
                title,
                subtitle,
                date
        );

        assertEquals(
                firstEvent,
                firstEvent
        );
        assertEquals(
                firstEvent.hashCode(),
                secondEvent.hashCode()
        );
    }

    @Test
    public void differenceTest() {
        Event firstEvent = new Event(
                1,
                "First event",
                "First subtitle",
                new Date()
        );
        Event secondEvent= new Event(
                2,
                "Second title",
                "Second subtitle",
                new Date()
        );

        assertNotEquals(
                firstEvent,
                secondEvent
        );
        assertNotEquals(
                firstEvent.hashCode(),
                secondEvent.hashCode()
        );
    }

}
