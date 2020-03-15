package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.Objects;

@DatabaseTable
public class Event extends EntityWithId {

    @DatabaseField
    private String title;

    @DatabaseField
    private String subtitle;

    @DatabaseField(dataType = DataType.DATE_STRING)
    private Date date;

    public Event(
            long id,
            @NonNull String title,
            @NonNull String subtitle,
            @NonNull Date date
    ) {
        super(id);
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    public Event(
            @NonNull String title,
            @NonNull String subtitle,
            @NonNull Date date
    ) {
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    public Event() {

    }

    public String title() {
        return title;
    }

    public String subtitle() {
        return subtitle;
    }

    public Date date() {
        return date;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(title, event.title)
                && Objects.equals(subtitle, event.subtitle)
                && Objects.equals(date, event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                title,
                subtitle,
                date
        );
    }

}
