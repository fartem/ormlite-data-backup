package com.smlnskgmail.jaman.ormlitedatabackup.logic.ormlite;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class OrmLiteDatabaseConfiguration {

    public static void main(@NonNull String[] args) throws Exception {
        OrmLiteConfigUtil.writeConfigFile("ormlite_config.txt");
    }

}
