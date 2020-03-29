package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities;

import com.j256.ormlite.field.DatabaseField;

abstract class EntityWithId {

    @SuppressWarnings("FieldCanBeLocal")
    @DatabaseField(generatedId = true)
    private long id;

    EntityWithId(long id) {
        this.id = id;
    }

    @SuppressWarnings("WeakerAccess")
    public EntityWithId() {

    }

}
