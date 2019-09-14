package com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.EventFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logs.Log;

import java.sql.SQLException;

public class EventHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView subtitle;

    private final ImageView delete;

    private Log log;

    EventHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.event_title);
        subtitle = itemView.findViewById(R.id.event_subtitle);
        delete = itemView.findViewById(R.id.event_delete);
    }

    void withLog(Log log) {
        this.log = log;
    }

    void bind(Event event) {
        title.setText(event.title());
        subtitle.setText(event.subtitle());

        delete.setOnClickListener(view -> {
            try {
                EventFactory.delete(event);
            } catch (SQLException e) {
                if (log != null) {
                    log.log(e);
                }
            }
        });
    }

}
