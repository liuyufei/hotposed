package com.keen.hotposed.support.HotposedUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Timer {

    public static String getTime() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.CHINA);
        return "[" + format.format(now) + "]";
    }
}
