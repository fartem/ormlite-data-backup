package com.smlnskgmail.jaman.ormlitedatabackup.logic.event.list;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities.Event;

public interface EventDeleteTarget {

    void eventDeleted(@NonNull Event event);

}
