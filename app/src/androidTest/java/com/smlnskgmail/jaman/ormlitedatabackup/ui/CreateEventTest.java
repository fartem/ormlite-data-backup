package com.smlnskgmail.jaman.ormlitedatabackup.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.ormlitedatabackup.MainActivity;
import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.support.L;
import com.smlnskgmail.jaman.ormlitedatabackup.ui.utils.ViewChildClick;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CreateEventTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class
    );

    private final String eventTitle = "Test title";
    private final String eventSubtitle = "Test subtitle";

    private long eventsAtStart;

    @Before
    public void saveEventsCount() {
        eventsAtStart = OrmLiteHelperFactory.databaseHelper().countOfEvents();
    }

    @Test
    public void runTest() {
        createNewEvent();
        validateLastEvent();
        deleteEvent();
    }

    private void createNewEvent() {
        onView(withId(R.id.main_fab_menu)).perform(click());
        delay();

        onView(withId(R.id.create_event)).perform(click());
        onView(withId(R.id.create_event)).perform(click()); // Cannot click on FAB in previous operation
        delay();

        onView(withId(R.id.new_event_title))
                .perform(
                        replaceText(eventTitle),
                        closeSoftKeyboard()
                );
        delay();

        onView(withId(R.id.new_event_subtitle))
                .perform(
                        replaceText(eventSubtitle),
                        closeSoftKeyboard()
                );
        delay();

        onView(withId(R.id.add_new_event)).perform(click());
        delay();

        checkEventsCount(eventsAtStart + 1);
    }

    private void validateLastEvent() {
        List<Event> events = OrmLiteHelperFactory.databaseHelper().allEvents();
        Event lastEvent = events.get(events.size() - 1);

        assertEquals(
                eventTitle,
                lastEvent.title()
        );
        assertEquals(
                eventSubtitle,
                lastEvent.subtitle()
        );

        assertTrue(
                lastEvent.date().before(Calendar.getInstance().getTime())
        );
    }

    private void deleteEvent() {
        onView(withId(R.id.events_list)).perform(RecyclerViewActions
                .actionOnItemAtPosition(
                        (int) eventsAtStart,
                        ViewChildClick.withChildId(R.id.event_delete)
                )
        );

        checkEventsCount(eventsAtStart);
    }

    private void checkEventsCount(long count) {
        assertEquals(
                count,
                OrmLiteHelperFactory.databaseHelper().countOfEvents()
        );
    }

    private void delay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            L.e(e);
        }
    }

}
