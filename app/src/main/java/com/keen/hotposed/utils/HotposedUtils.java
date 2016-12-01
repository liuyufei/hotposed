package com.keen.hotposed.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HotposedUtils {

    public static String command(String cmd) {

        StringBuilder result = new StringBuilder();

        Runtime runtime = Runtime.getRuntime();

        try {
            Process process = runtime.exec(cmd);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
