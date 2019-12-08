package com.smlnskgmail.jaman.ormlitedatabackup.logic.main.eventslist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.db.entities.Event;

public class EventHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView subtitle;

    private final ImageView delete;

    private final EventDeleteTarget eventDeleteTarget;

    EventHolder(@NonNull View itemView, @NonNull EventDeleteTarget eventDeleteTarget) {
        super(itemView);
        title = itemView.findViewById(R.id.event_title);
        subtitle = itemView.findViewById(R.id.event_subtitle);
        delete = itemView.findViewById(R.id.event_delete);

        this.eventDeleteTarget = eventDeleteTarget;
    }

    void bind(@NonNull Event event) {
        title.setText(event.title());
        subtitle.setText(event.subtitle());

        delete.setOnClickListener(view -> eventDeleteTarget.eventDeleted(event));
    }

    public interface EventDeleteTarget {

        void eventDeleted(@NonNull Event event);

    }

}
