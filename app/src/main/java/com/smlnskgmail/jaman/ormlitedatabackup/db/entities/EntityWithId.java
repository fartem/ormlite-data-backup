package com.smlnskgmail.jaman.ormlitedatabackup.db.entities;

import com.j256.ormlite.field.DatabaseField;

public abstract class EntityWithId {

    @DatabaseField(generatedId = true)
    private long id = -1;

    public EntityWithId(long id) {
        this.id = id;
    }

    public EntityWithId() {}

}
