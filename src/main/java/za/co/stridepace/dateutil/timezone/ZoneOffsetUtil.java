package za.co.stridepace.dateutil.timezone;

public class ZoneOffsetUtil {

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
        int absoluteMinutes = Math.abs((int)(offsetDecimalPart * 60));
        String minutes = absoluteMinutes < 10 ? absoluteMinutes + "0" : String.valueOf(absoluteMinutes);
        return prefix + hours + ":" + minutes;
    }
}
