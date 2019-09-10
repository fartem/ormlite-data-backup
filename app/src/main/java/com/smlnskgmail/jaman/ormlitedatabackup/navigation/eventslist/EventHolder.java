package com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;

class EventHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView subtitle;

    private final ImageView delete;

    EventHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.event_title);
        subtitle = itemView.findViewById(R.id.event_subtitle);
        delete = itemView.findViewById(R.id.event_delete);
    }

    void bind(Event event) {
        title.setText(event.title());
        subtitle.setText(event.subtitle());

        delete.setOnClickListener(view -> {

        });
    }

}
