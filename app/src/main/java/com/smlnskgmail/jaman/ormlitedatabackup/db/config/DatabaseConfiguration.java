package com.smlnskgmail.jaman.ormlitedatabackup.db.config;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfiguration {

    public static void main(String[] args) throws Exception {
        OrmLiteConfigUtil.writeConfigFile("ormlite_config.txt");
    }

}
