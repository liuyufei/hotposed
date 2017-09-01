# Hotposed

Hotposed : A Xposed based tool loads your Xposed plugins in a hot-plug way.

## Description

When you write a Xposed module, you need to do a soft reboot operation which provied by Xposed Installer to get the module works. The fact is that after every change of the module you have to repeat the soft reboot operation. This really bothered me and wasted a lot of time. So I write the tool **Hotposed** to load the modules dynamiclly and it just need to **restart the application** you want to hook.

Hotposed itself is a Xposed module, you should install it under the Xposed framework if you want to use the dynamic loading feature. The implementation of Hotposed is really easy which I just use some configuration files and the android dynamic class loader mechanism（``DexClassLoader``）and it works well.

## Usage

### 1. Install the Hotposed module first and soft reboot to let Hotposed works

### 2. Write a Hotposed plugin (An android app project) which is really easy and install the plugin to the phone

The Hotposed plugin is a normal Android application, it contains the hook logic. The plugin must have a entrance class which will be written into the Hotposed config file and the class should implements the ```IHotposedPlugin``` interface. 

An example of the class :

```
public class Test implements IHotposedPlugin {

    public static final String TAG = "HWLogHook";

    XC_MethodHook hook = new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            Log.i(TAG, param.args[0] + "\t\t" + param.args[1]);
        }
    };

    @Override
    public void startHotposedPlugin(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        Log.i("HotposedPlugin", loadPackageParam.packageName + " changed twice with hook");

        XposedHelpers.findAndHookMethod("com.huawei.wallet.f.c.c", loadPackageParam.classLoader, "i", String.class, String.class, boolean.class, hook);
        XposedHelpers.findAndHookMethod("com.huawei.wallet.f.c.c", loadPackageParam.classLoader, "h", String.class, String.class, boolean.class, hook);
        XposedHelpers.findAndHookMethod("com.huawei.wallet.f.c.c", loadPackageParam.classLoader, "f", String.class, String.class, Throwable.class, hook);

    }

}
```

The class has to implements the method ``startHotposedPlugin`` and it will get the param ``loadPackageParam`` which is the same to the Xposed method ``handleLoadPackage``, then you can write the hook logic.

What you have to pay attention to :

The plugin should contain hotposed.jar and XposedBridgeApi-54.jar and config it as provided in the build.gradle

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.2.1'
    provided files('lib/XposedBridgeApi-54.jar')
    provided files('lib/hotposed.jar')
}
```

An example of the plugin app is here [hotposed-plugin](https://github.com/liuyufei/hotposed-plugin).

### 3. Write Hotposed's config file

What a Hotposed configuration file looks like :

```
File : hotposed.config
===============================================
# hotposed config file

com.huawei.wallet:com.keen.hotposedpluginexample:com.keen.hotposedpluginexample.Test:on
===============================================
```

Each line of the config file is a plugin configuration, the line contains four items spilted by symbol ":"

**Item[0]** : The package name of the application which you want to hook. 

**Item[1]** : The package name of the Hotposed plugin which used to hook the application

**Item[2]** : The entrance class of the plugin which implements the ```IHotposedPlugin``` interface provided by Hotposed

**Item[3]** : An on/off switch to control whether the configuration of this line works

Push the config file to the correct dir:

```
adb push hotposed.config /sdcard/hotposed/
```

### 4. Restart the application you want to hook and see the changes

Eevry time after you changed your hook logic, you just need to reinstall the plugin and restart the application, **NO MORE REBOOT!**
