package com.smlnskgmail.jaman.ormlitedatabackup.logic.event.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.OrmLiteHelperFactory;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventHolder> implements EventDeleteTarget {

    private final List<Event> events;

    public EventsAdapter(@NonNull List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        return new EventHolder(LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_event,
                        parent,
                        false
                ),
                this
        );
    }

    @Override
    public void onBindViewHolder(
            @NonNull EventHolder holder,
            int position
    ) {
        holder.bind(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public void eventDeleted(@NonNull Event event) {
        OrmLiteHelperFactory.databaseHelper().deleteEvent(event);

        int eventIndex = events.indexOf(event);
        events.remove(eventIndex);
        notifyItemRemoved(eventIndex);
    }

}
