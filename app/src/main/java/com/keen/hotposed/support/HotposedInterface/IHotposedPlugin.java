package com.keen.hotposed.support.HotposedInterface;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public interface IHotposedPlugin {

    void startHotposedPlugin(XC_LoadPackage.LoadPackageParam loadPackageParam);

}
