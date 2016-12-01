package com.keen.hotposed.common;

import android.os.Environment;

import java.io.File;

public class HotposedCommon {

    public static final String TAG = "Hotposed.";

    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;

    public static final String CONFIG_PATH = BASE_PATH + "hotposed" + File.separator + "hotposed.config";

    public static final String PLUGIN_DIR = BASE_PATH + "hotposed" + File.separator + "plugin";

    public static final String LOG_DIR = BASE_PATH + "hotposed" + File.separator + "log";

    public static void init() {

        checkDir(PLUGIN_DIR);
        checkDir(LOG_DIR);

    }

    public static void checkDir(String path) {

        File pluginDir = new File(path);

        if (!pluginDir.exists()) {
            pluginDir.mkdirs();
        }
    }

}
