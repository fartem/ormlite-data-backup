package com.smlnskgmail.jaman.ormlitedatabackup.db.entities;

import com.j256.ormlite.field.DatabaseField;

public abstract class EntityWithId {

    @SuppressWarnings("FieldCanBeLocal")
    @DatabaseField(generatedId = true)
    private long id;

    public EntityWithId(long id) {
        this.id = id;
    }

    @SuppressWarnings("WeakerAccess")
    public EntityWithId() {

    }

}
