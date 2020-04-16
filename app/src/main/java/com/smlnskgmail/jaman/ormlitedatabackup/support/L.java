package com.smlnskgmail.jaman.ormlitedatabackup.support;

import android.util.Log;

import androidx.annotation.NonNull;

public class L {

    public static void e(@NonNull Throwable e) {
        Log.e(
                "ormlite-data-backup",
                "---",
                e
        );
    }

}
