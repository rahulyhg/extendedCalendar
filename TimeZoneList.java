/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calbul;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.swing.JComboBox;

/**
 *
 * @author Pan-Kawia
 */

//wypełnia listę rozwijalną strefami czasowymi
public class TimeZoneList {
    public static void fillComboBoxWithZone(JComboBox box) {
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            box.addItem(displayTimeZone(TimeZone.getTimeZone(id)));
        }
    }

    public static String displayTimeZone(TimeZone tz) {
        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);
        minutes = Math.abs(minutes);
        String result = "";
        
        if (hours > 0) {
            result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
        }

        return result;
    }
}
