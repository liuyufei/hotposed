package com.keen.hotposed.core;

import android.util.Log;

import com.keen.hotposed.common.HotposedCommon;
import com.keen.hotposed.support.HotposedInterface.IHotposedPlugin;

import dalvik.system.DexClassLoader;

public class HotposedLoader {

    public static final String TAG = HotposedCommon.TAG + "HotposedLoader";

    public static IHotposedPlugin loadPlugin(String packageName, String codePath, String className, ClassLoader classLoader) {

        DexClassLoader loader = new DexClassLoader(codePath, "/data/data/" + packageName, null, classLoader);

        IHotposedPlugin pluginInstance = null;

        try {

            Class plugin = loader.loadClass(className);

            Log.i(TAG, "Load class ... [ Name : " + plugin.getName() + " ]");

            pluginInstance = (IHotposedPlugin) plugin.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return pluginInstance;
    }

}
