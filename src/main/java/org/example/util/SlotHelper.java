package org.example.util;

import org.example.entity.Slots;

import java.util.ArrayList;
import java.util.List;

public class SlotHelper {
    public static List<Slots> generateSlots(String startTime, String endTime) {
        int startTimeHour = Integer.parseInt(startTime.split(":")[0]);
        int startTimeMinute = Integer.parseInt(startTime.split(":")[1]);
        int endTimeHour = Integer.parseInt(endTime.split(":")[0]);
        int endTimeMinute = Integer.parseInt(endTime.split(":")[1]);
        List<Slots> slotsList = new ArrayList<>();
        for (int i = startTimeHour; i <= endTimeHour; i++) {
            if (i == endTimeHour) {
                if (endTimeMinute > 0) {
                    Slots slots = new Slots(getFormattedHourMinute(i, 0), getFormattedHourMinute(i, 30));
                    slotsList.add(slots);
                }
            } else {
                if (i > startTimeHour || (startTimeMinute == 0 && i == startTimeHour)) {
                    Slots slots = new Slots(getFormattedHourMinute(i, 0), getFormattedHourMinute(i, 30));
                    slotsList.add(slots);
                }
                Slots slots = new Slots(getFormattedHourMinute(i, 30), getFormattedHourMinute(i + 1, 0));
                slotsList.add(slots);
            }
        }
        return slotsList;
    }

    private static String getFormattedHourMinute(int hour, int minute) {
        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour);
        sb.append(":");
        if (minute <= 9) {
            sb.append("0");
        }
        sb.append(minute);
        return sb.toString();
    }
}
