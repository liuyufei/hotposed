package com.keen.hotposed.core;

public class HotposedConfigData {

    public String packageName;
    public String pluginPackageName;
    public String className;

    public HotposedConfigData(String packageName, String pluginPackageName, String className) {
        this.packageName = packageName;
        this.pluginPackageName = pluginPackageName;
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPluginPackageName() {
        return pluginPackageName;
    }

    public void setPluginPackageName(String pluginPackageName) {
        this.pluginPackageName = pluginPackageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
