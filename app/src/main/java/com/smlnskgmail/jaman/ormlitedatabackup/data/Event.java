package com.smlnskgmail.jaman.ormlitedatabackup.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.smlnskgmail.jaman.ormlitedatabackup.db.entities.EntityWithId;

import java.util.Date;

@DatabaseTable
public class Event extends EntityWithId {

    @DatabaseField
    private String title;

    @DatabaseField
    private String subtitle;

    @DatabaseField(dataType = DataType.DATE_STRING)
    private Date date;

    public Event(long id, String title, String subtitle, Date date) {
        super(id);
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    public Event(String title, String subtitle, Date date) {
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    public Event() {}

    public String title() {
        return title;
    }

    public String subtitle() {
        return subtitle;
    }

    public Date date() {
        return date;
    }

}
