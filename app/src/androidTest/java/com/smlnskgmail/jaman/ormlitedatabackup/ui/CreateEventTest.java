package com.smlnskgmail.jaman.ormlitedatabackup.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.EventFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.features.db.BaseDBTest;
import com.smlnskgmail.jaman.ormlitedatabackup.navigation.MainActivity;
import com.smlnskgmail.jaman.ormlitedatabackup.ui.utils.ViewChildClick;

import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateEventTest extends BaseDBTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private final String eventTitle = "Test event's title";
    private final String eventSubtitle = "Test event's subtitle";

    private long eventsAtStart = 0;

    @Test
    public void runTest() throws SQLException {
        saveEventsCount();
        createNewEvent();
        validateLastEvent();
        deleteEvent();
    }

    private void saveEventsCount() throws SQLException {
        eventsAtStart = EventFactory.sizeOf();
    }

    private void createNewEvent() throws SQLException {
        onView(withId(R.id.main_fab_menu)).perform(click());
        delay();

        onView(withId(R.id.create_event)).perform(click());
        onView(withId(R.id.create_event)).perform(click()); // Cannot click on FAB in previous operation
        delay();

        onView(withId(R.id.new_event_title)).perform(typeText(eventTitle), closeSoftKeyboard());
        delay();

        onView(withId(R.id.new_event_subtitle)).perform(typeText(eventSubtitle), closeSoftKeyboard());
        delay();

        onView(withId(R.id.add_new_event)).perform(click());
        delay();

        checkEventsCount(eventsAtStart + 1);
    }

    private void validateLastEvent() throws SQLException {
        List<Event> events = EventFactory.allEvents();
        Event lastEvent = events.get(events.size() - 1);

        assertEquals(lastEvent.title(), eventTitle);
        assertEquals(lastEvent.subtitle(), eventSubtitle);

        assertTrue(lastEvent.date().before(Calendar.getInstance().getTime()));
    }

    private void deleteEvent() throws SQLException {
        onView(withId(R.id.events_list)).perform(RecyclerViewActions
                .actionOnItemAtPosition((int) eventsAtStart, ViewChildClick.withChildId(R.id.event_delete)));

        checkEventsCount(eventsAtStart);
    }

    private void checkEventsCount(long count) throws SQLException {
        assertEquals(count, EventFactory.sizeOf());
    }

    private void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
