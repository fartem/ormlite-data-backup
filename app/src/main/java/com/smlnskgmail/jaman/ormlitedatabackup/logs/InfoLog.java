package com.smlnskgmail.jaman.ormlitedatabackup.logs;

public class InfoLog implements Log {

    @Override
    public void log(Exception e) {
        android.util.Log.i(getClass().getCanonicalName(), "->", e);
    }

}
