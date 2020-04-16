package com.smlnskgmail.jaman.ormlitedatabackup.runner;

import android.os.Bundle;

import androidx.test.runner.AndroidJUnitRunner;

import com.smlnskgmail.jaman.ormlitedatabackup.support.L;

import java.lang.reflect.Method;

public class AndroidJacocoTestRunner extends AndroidJUnitRunner {

    @Override
    public void finish(int resultCode, Bundle results) {
        try {
            Class rt = Class.forName("org.jacoco.agent.rt.RT");

            @SuppressWarnings("unchecked")
            Method getAgent = rt.getMethod("getAgent");
            Method dump = getAgent.getReturnType().getMethod(
                    "dump",
                    boolean.class
            );
            Object agent = getAgent.invoke(null);
            dump.invoke(agent, false);
        } catch (Throwable e) {
            L.e(e);
        }
        super.finish(resultCode, results);
    }

}
