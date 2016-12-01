package com.keen.hotposed.utils;

import com.keen.hotposed.core.HotposedConfigData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {

    public static Map<String, HotposedConfigData> loadHotposedConfig(String path) {

        File config = new File(path);

        Map<String, HotposedConfigData> map = new HashMap<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(config));

            String line;

            /*
             * An example line of the config file
             *
             * # Hotposed config file test line
             *
             * com.hotposed.test:com.hotposed.plugin:com.plugin.main:on
             */

            while((line = reader.readLine()) != null) {

                if (line.startsWith("#") || line.trim().equals("")) continue;

                if (line.split(":").length != 4) continue;

                String[] configData = line.split(":");

                String packageName = configData[0];
                String pluginPackageName = configData[1];
                String className = configData[2];
                String switchStatus = configData[3];

                if (switchStatus.equals("on")) {
                    map.put(packageName, new HotposedConfigData(packageName, pluginPackageName, className));
                }

            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }
}
