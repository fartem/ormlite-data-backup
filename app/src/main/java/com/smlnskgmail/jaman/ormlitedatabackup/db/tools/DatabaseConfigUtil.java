package com.smlnskgmail.jaman.ormlitedatabackup.db.tools;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil {

    public static void main(String[] args) throws Exception {
        OrmLiteConfigUtil.writeConfigFile("ormlite_config.txt");
    }

}
