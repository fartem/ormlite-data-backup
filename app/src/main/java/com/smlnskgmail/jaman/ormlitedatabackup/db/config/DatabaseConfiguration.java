package com.smlnskgmail.jaman.ormlitedatabackup.db.config;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfiguration {

    public static void main(@NonNull String[] args) throws Exception {
        OrmLiteConfigUtil.writeConfigFile("ormlite_config.txt");
    }

}
