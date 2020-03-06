package com.smlnskgmail.jaman.ormlitedatabackup.logic.event.actions.create;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;

public interface EventCreateTarget {

    void eventAdded(@NonNull Event event);

}
