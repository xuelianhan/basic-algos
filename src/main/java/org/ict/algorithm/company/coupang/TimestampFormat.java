package org.ict.algorithm.company.coupang;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
public class TimestampFormat {

    /**
     * Passing a timestamp, which cannot be used with methods such as the built-in function Date,
     * returns a formatted date YYYY:MM:DD HH:mm:ss
     * @param timestamp
     * @return
     */
    public String format(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY:MM:DD HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }
}
