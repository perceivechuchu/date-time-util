package za.co.stridepace.dateutil.timezone;


import za.co.stridepace.dateutil.domain.TimeZoneDetail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class TimeZoneUtil {

    private static final String TIMEZONE_ID_PREFIXES = "^(Africa|America|Asia|Atlantic|Australia|Europe|Indian|Pacific)/.*";
    private static List<TimeZone> timeZones = null;

    private TimeZoneUtil() {
    }

    public static List<TimeZoneDetail> getAllTimeZones() {
        if (timeZones == null) {
            loadTimeZones();
        }
        return timeZones.stream().map(timeZone -> TimeZoneDetail.getInstance(timeZone.getID(), timeZone.getDisplayName(), TimeZoneUtil.getTimeZoneAbbreviation(timeZone.getID()), timeZone.getRawOffset() / 3600000)).collect(Collectors.toList());
    }

    private static void loadTimeZones() {
        timeZones = new ArrayList<>();
        final String[] timeZoneIds = TimeZone.getAvailableIDs();
        for (final String id : timeZoneIds) {
            if (id.matches(TIMEZONE_ID_PREFIXES)) {
                timeZones.add(TimeZone.getTimeZone(id));
            }
        }
        timeZones.sort(Comparator.comparing(TimeZone::getID));
    }

    public static boolean isTimeZoneValid(String timeZoneId) {
        boolean flag = false;
        for (TimeZone tomeZone : timeZones) {
            if (tomeZone.getID().equals(timeZoneId)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static String getTimeZoneAbbreviation(String zoneId) {
        return java.util.TimeZone.getTimeZone(zoneId).getDisplayName(false, java.util.TimeZone.SHORT);
    }

}
