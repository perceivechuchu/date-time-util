package za.co.stridepace.dateutil.timezone;

/**
 * This class is a utility for time zone offset
 *
 * @author Perceive Chuchu
 */
public class ZoneOffsetUtil {

    private ZoneOffsetUtil() {
    }

    /**
     * Converts offset from integer value to string offset
     *
     * @param rawOffset the raw offset as received from TimeZone java library
     * @return the calculated string offset
     * @since 1.0.0
     */
    protected static String deduceZoneOffset(int rawOffset) {
        double offsetHoursMinutes = rawOffset / 3600000.0;
        String prefix = "";
        int offsetIntegerPart = (int) offsetHoursMinutes;
        if (offsetIntegerPart > 0) {
            prefix = "+";
        } else if (offsetIntegerPart < 0) {
            prefix = "-";
        }
        double offsetDecimalPart = offsetHoursMinutes - offsetIntegerPart;
        String hours = offsetIntegerPart < 10 ? "0" + Math.abs(offsetIntegerPart) : String.valueOf(offsetIntegerPart);
        int absoluteMinutes = Math.abs((int) (offsetDecimalPart * 60));
        String minutes = absoluteMinutes < 10 ? absoluteMinutes + "0" : String.valueOf(absoluteMinutes);
        return prefix + hours + ":" + minutes;
    }
}
