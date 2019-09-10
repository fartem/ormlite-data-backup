package com.smlnskgmail.jaman.ormlitedatabackup.logs;

public class ErrorLog implements Log {

    @Override
    public void log(Exception e) {
        android.util.Log.e(getClass().getCanonicalName(), "->", e);
    }

}
