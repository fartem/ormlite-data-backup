package com.smlnskgmail.jaman.ormlitedatabackup.navigation.eventslist;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;

public interface EventDeleteTarget {

    void eventDeleted(@NonNull Event event);

}
