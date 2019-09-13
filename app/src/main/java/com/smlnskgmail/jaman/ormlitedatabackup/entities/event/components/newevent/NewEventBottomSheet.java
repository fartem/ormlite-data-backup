package com.smlnskgmail.jaman.ormlitedatabackup.entities.event.components.newevent;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.components.BaseBottomSheet;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.EventFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;

public class NewEventBottomSheet extends BaseBottomSheet {

    private NewEventTarget newEventTarget;
    private Log log;

    public void setupNewEventTarget(NewEventTarget newEventTarget) {
        this.newEventTarget = newEventTarget;
    }

    public void setupLog(Log log) {
        this.log = log;
    }

    @Override
    public void init(View view) {
        Calendar calendar = Calendar.getInstance();

        EditText title = view.findViewById(R.id.new_event_title);
        EditText subtitle = view.findViewById(R.id.new_event_subtitle);

        TextView date = view.findViewById(R.id.new_event_date);
        TextView time = view.findViewById(R.id.new_event_time);

        date.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime()));
        time.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime()));

        view.findViewById(R.id.add_new_event_close).setOnClickListener(button -> dismiss());
        view.findViewById(R.id.add_new_event).setOnClickListener(button -> {
            String newEventTitle = title.getText().toString();
            String newEventSubtitle = subtitle.getText().toString();
            Event event = new Event(newEventTitle, newEventSubtitle, calendar.getTime());
            try {
                EventFactory.save(event);
                newEventTarget.newEventAdded();
            } catch (SQLException e) {
                if (log != null) {
                    log.log(e);
                }
            }
        });
    }

    @Override
    public int layoutResId() {
        return R.layout.bottom_sheet_new_event;
    }

}
