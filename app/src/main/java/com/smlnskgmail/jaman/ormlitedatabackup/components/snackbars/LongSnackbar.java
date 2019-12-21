package com.smlnskgmail.jaman.ormlitedatabackup.components.snackbars;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

public class LongSnackbar {

    private final View view;
    private final String text;

    public LongSnackbar(
            @NonNull View view,
            @NonNull String text
    ) {
        this.view = view;
        this.text = text;
    }

    public void show() {
        Snackbar.make(
                view,
                text,
                Snackbar.LENGTH_LONG
        ).show();
    }

}
