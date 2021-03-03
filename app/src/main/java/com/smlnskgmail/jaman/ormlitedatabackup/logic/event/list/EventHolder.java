package com.smlnskgmail.jaman.ormlitedatabackup.logic.event.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smlnskgmail.jaman.ormlitedatabackup.R;
import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;

class EventHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvSubtitle;

    private final ImageView ivDelete;

    private final EventDeleteTarget eventDeleteTarget;

    EventHolder(
            @NonNull View itemView,
            @NonNull EventDeleteTarget eventDeleteTarget
    ) {
        super(itemView);
        this.eventDeleteTarget = eventDeleteTarget;

        tvTitle = itemView.findViewById(R.id.event_title);
        tvSubtitle = itemView.findViewById(R.id.event_subtitle);
        ivDelete = itemView.findViewById(R.id.event_delete);
    }

    void bind(@NonNull Event event) {
        tvTitle.setText(event.title());
        tvSubtitle.setText(event.subtitle());

        ivDelete.setOnClickListener(view -> eventDeleteTarget.eventDeleted(event));
    }

}
