package com.keen.hotposed.core;

import android.util.Log;

import com.keen.hotposed.common.HotposedCommon;
import com.keen.hotposed.support.HotposedInterface.IHotposedPlugin;
import com.keen.hotposed.utils.ConfigLoader;

import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.keen.hotposed.utils.HotposedUtils.command;

public class HotposedEntrance implements IXposedHookLoadPackage {

    public static final String TAG = HotposedCommon.TAG + "HotposedEntrance";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        HotposedCommon.init();

        String packageName = loadPackageParam.packageName;

        Map<String, HotposedConfigData> config = ConfigLoader.loadHotposedConfig(HotposedCommon.CONFIG_PATH);

        if (config.keySet().contains(packageName)) {

            HotposedConfigData data = config.get(packageName);

            IHotposedPlugin plugin = HotposedLoader.loadPlugin(packageName, getCodePath(data.getPluginPackageName()), data.getClassName(), HotposedEntrance.class.getClassLoader());

            plugin.startHotposedPlugin(loadPackageParam);

            Log.i(TAG, "Loading plugin ...  [ PackageName : " + packageName + "    PluginPackageName : " + data.pluginPackageName + "    Loading class : " + data.className + " ]");
        }

    }

    public String getCodePath(String pluginPackageName) {

        return command("pm path " + pluginPackageName).replace("\n", "").split(":")[1];

    }

}
