package com.smlnskgmail.jaman.ormlitedatabackup.tools;

import android.util.Log;

import androidx.annotation.NonNull;

public class L {

    public static void e(@NonNull Exception e) {
        Log.e(
                "ormlite-data-backup",
                "---",
                e
        );
    }

}
