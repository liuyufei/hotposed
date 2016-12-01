package com.keen.hotposed.support.HotposedUtils;

import com.keen.hotposed.common.HotposedCommon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HotposedLog {

    public static void log(String tag, String content, String packageName, String fileName) {

        String logPath = HotposedCommon.LOG_DIR + File.separator + packageName + File.separator + fileName + ".log";

        checkDir(HotposedCommon.LOG_DIR + File.separator + packageName);

        File logFile = new File(logPath);

        if(!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter writer = null;

        try {
            writer = new FileWriter(logFile, true);
            writer.append(Timer.getTime() + tag + " : " + content + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void checkDir(String path) {

        File pluginDir = new File(path);

        if (!pluginDir.exists()) {
            pluginDir.mkdirs();
        }
    }
}
