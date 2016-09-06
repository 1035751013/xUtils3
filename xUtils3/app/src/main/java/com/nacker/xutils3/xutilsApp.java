package com.nacker.xutils3;

import android.app.Application;

import org.xutils.x;

/**
 * Created by nacker on 16/9/6.
 */
public class xutilsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);

    }
}
