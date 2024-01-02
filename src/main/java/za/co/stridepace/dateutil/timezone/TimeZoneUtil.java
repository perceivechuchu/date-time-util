package za.co.stridepace.dateutil.timezone;


import za.co.stridepace.dateutil.commons.model.ValidationEntry;
import za.co.stridepace.dateutil.commons.util.ValidationUtil;
import za.co.stridepace.dateutil.constant.ErrorMessages;
import za.co.stridepace.dateutil.model.TimeZoneDetail;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is a utility for time zones
 *
 * @since 1.0.0
 *
 * @author Perceive Chuchu
 */
public final class TimeZoneUtil {

    /**
     * The regex for time zone id prefixes
     *
     * @since 1.0.0
     */
    private static final String TIMEZONE_ID_PREFIXES = "^(Africa|America|Asia|Atlantic|Australia|Europe|Indian|Pacific)/.*";

    /**
     * The GMT time zone value
     *
     * @since 1.0.0
     */
    private static final String ZONE_ID_GMT = "GMT";

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
     * Checks if a time zone id is valid
     *
     * @param timeZoneId    the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the boolean value showing the validation result: "true or false"
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static boolean isValidTimeZoneId(final String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        return (timeZoneId.equals(ZONE_ID_GMT) || !TimeZone.getTimeZone(timeZoneId).getID().equals(ZONE_ID_GMT));
    }

    /**
     * Gets the abbreviation for a time zone
     *
     * @param timeZoneId    the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the abbreviation for a time zone e.g. abbreviation for zone id "Africa/Johannesburg" is "SAST"
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static String getTimeZoneAbbreviation(final String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        if (!isValidTimeZoneId(timeZoneId)) {
            throw new IllegalArgumentException(ErrorMessages.TIME_ZONE_ID_INVALID);
        }
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        return timeZone.getDisplayName(false, java.util.TimeZone.SHORT);
    }

    /**
     * Search time zones by the offset text e.g. search by "+02:00"
     *
     * @param offsetText    The difference in hours and minutes from Coordinated Universal Time (UTC) e.g. "+02:00"
     * @return the list of time zone details
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static List<TimeZoneDetail> searchTimeZonesByOffsetText(final String offsetText) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(offsetText, ErrorMessages.TIME_OFFSET_TEXT_EMPTY));
        List<TimeZoneDetail> timeZoneDetails = TimeZoneUtil.getAllTimeZones();
        return timeZoneDetails.stream().filter(timeZoneDetail -> timeZoneDetail.getOffsetText().equals(offsetText)).collect(Collectors.toList());
    }

}
