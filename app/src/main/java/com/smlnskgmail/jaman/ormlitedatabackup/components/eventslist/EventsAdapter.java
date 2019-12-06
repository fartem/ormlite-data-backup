package com.smlnskgmail.jaman.ormlitedatabackup.components.eventslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.Event;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventHolder> {

    private final List<Event> events;

    private final EventHolder.EventDeleteTarget eventDeleteTarget;

    public EventsAdapter(
            @NonNull List<Event> events,
            @NonNull EventHolder.EventDeleteTarget eventDeleteTarget
    ) {
        this.events = events;
        this.eventDeleteTarget = eventDeleteTarget;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventHolder(LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_event,
                        parent,
                        false
                ), eventDeleteTarget);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.bind(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
