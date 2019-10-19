package com.smlnskgmail.jaman.ormlitedatabackup.entities.event.ui;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.entities.event.Event;

public interface EventCreateTarget {

    void eventAdded(@NonNull Event event);

}
