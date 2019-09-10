package com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventHolder> {

    private final List<Event> events;

    public EventsAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false));
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
