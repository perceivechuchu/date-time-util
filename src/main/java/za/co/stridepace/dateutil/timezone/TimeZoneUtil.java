package za.co.stridepace.dateutil.timezone;


import za.co.stridepace.dateutil.model.TimeZoneDetail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * This class is a utility for time zones
 *
 * @since 1.0.0
 */
public final class TimeZoneUtil {

    /**
     * The regex for time zone id prefixes
     *
     * @since 1.0.0
     */
    private static final String TIMEZONE_ID_PREFIXES = "^(Africa|America|Asia|Atlantic|Australia|Europe|Indian|Pacific)/.*";

    /**
     * The list that holds the retrieved time zones
     *
     * @since 1.0.0
     */
    private static List<TimeZone> timeZones = null;

    private TimeZoneUtil() {
    }

    /**
     * Gets all available time zones. This was implemented as a singleton to improve performance by fetching the already existing list in memory
     *
     * @return the list of all time zone details
     * @since 1.0.0
     */
    public static List<TimeZoneDetail> getAllTimeZones() {
        if (timeZones == null) {
            loadTimeZones();
        }
        return timeZones.stream().map(timeZone -> TimeZoneDetail.getInstance(timeZone.getID(), timeZone.getDisplayName(), getTimeZoneAbbreviation(timeZone.getID()), timeZone.getRawOffset(), ZoneOffsetUtil.deduceZoneOffset(timeZone.getRawOffset()))).collect(Collectors.toList());
    }

    /**
     * Loads and sorts time zones
     *
     * @since 1.0.0
     */
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

    /**
     * Checks is a time zone id is valid
     *
     * @return the boolean value showing the validation result: "true or false"
     * @since 1.0.0
     */
    public static boolean isValidTimeZoneId(String timeZoneId) {
        boolean flag = false;
        for (TimeZone tomeZone : timeZones) {
            if (tomeZone.getID().equals(timeZoneId)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Gets abbreviation for a time zone
     *
     * @return the abbreviation for a time zone e.g. abbreviation for zone id "Africa/Johannesburg" is "SAST"
     * @since 1.0.0
     */
    public static String getTimeZoneAbbreviation(String timeZoneId) {
        return java.util.TimeZone.getTimeZone(timeZoneId).getDisplayName(false, java.util.TimeZone.SHORT);
    }

}
