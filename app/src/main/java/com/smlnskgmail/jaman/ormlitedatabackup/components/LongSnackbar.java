package com.smlnskgmail.jaman.ormlitedatabackup.components;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class LongSnackbar {

    private View view;
    private String text;

    public LongSnackbar(View view, String text) {
        this.view = view;
        this.text = text;
    }

    public void show() {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

}
