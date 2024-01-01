package za.co.stridepace.dateutil.converter;

import za.co.stridepace.dateutil.commons.model.ValidationEntry;
import za.co.stridepace.dateutil.commons.util.ValidationUtil;
import za.co.stridepace.dateutil.constant.ErrorMessages;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * This class is a utility for date conversions
 *
 * @since 1.0.0
 *
 * @author Perceive Chuchu
 */
@SuppressWarnings("unused")
public final class DateConverter {

    /**
     * The zulu date format pattern
     */
    public static final String YYYY_MM_DD_HH_MM_SS_SSSXXX = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    /**
     * The UTC zone id
     */
    private static final String ZONE_ID_UTC = "UTC";

    private DateConverter() {
    }

    /**
     * Converts Local Date Time to UTC Zoned Date Time in text
     *
     * @param localDateTime   the local date time to be converted.
     * @param localTimeZoneId the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @return the local date time text obtained from the conversion.
     * @throws IllegalArgumentException exception thrown when required parameter is missing.
     * @since 1.0.0
     */
    public static String convertToUTCZonedDateTimeText(final LocalDateTime localDateTime, final String localTimeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(localTimeZoneId, ErrorMessages.LOCAL_TIME_ZONE_ID_EMPTY));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSSXXX);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(localTimeZoneId));
        dateFormatter.format(zonedDateTime);
        return dateFormatter.format(zonedDateTime.withZoneSameInstant(ZoneId.of(ZONE_ID_UTC)));
    }

    /**
     * Converts Local Date Time to UTC Zoned Date Time
     *
     * @param localDateTime   the local date time to be converted
     * @param localTimeZoneId the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @return the zoned date time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if the result exceeds the supported range
     * @since 1.0.0
     */
    public static ZonedDateTime convertToUTCZonedDateTime(final LocalDateTime localDateTime, final String localTimeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(localTimeZoneId, ErrorMessages.LOCAL_TIME_ZONE_ID_EMPTY));
        return localDateTime.atZone(ZoneId.of(localTimeZoneId));
    }

    /**
     * Converts Local Date Time to Zoned Date Time. You also provide the target time zone the local date time is to be converted
     *
     * @param localDateTime    the local date time to be converted
     * @param localTimeZoneId  the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @param targetTimeZoneId the target time zone id to be applied to the resulting zoned date time e.g. "Africa/Johannesburg"
     * @return the zoned date time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if the result exceeds the supported range
     * @since 1.0.0
     */
    public static ZonedDateTime convertToZonedDateTime(final LocalDateTime localDateTime, final String localTimeZoneId, final String targetTimeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(localTimeZoneId, ErrorMessages.LOCAL_TIME_ZONE_ID_EMPTY),
                ValidationEntry.getInstance(targetTimeZoneId, ErrorMessages.TARGET_TIME_ZONE_ID_EMPTY));
        return localDateTime.atZone(ZoneId.of(localTimeZoneId)).withZoneSameInstant(ZoneId.of(targetTimeZoneId));
    }

    /**
     * Converts UTC Zoned Date Time (Zulu time) to Local Date Time for the supplied Zone Id
     *
     * @param zonedDateTimeText the zoned date time text to be converted e.g. "2023-11-10T10:00:00.254Z"
     * @param localZoneId       the local time zone id for the supplied zoned date time e.g. "Africa/Johannesburg"
     * @return the local date time obtained from the conversion
     * @throws IllegalArgumentException exception thrown when required parameter is missing
     * @throws DateTimeException        exception thrown if the result exceeds the supported date range
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTimeWithZone(final String zonedDateTimeText, final String localZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(zonedDateTimeText, ErrorMessages.ZONED_DATE_TIME_TEXT), ValidationEntry.getInstance(localZoneId, ErrorMessages.LOCAL_TIME_ZONE_ID_EMPTY));
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(zonedDateTimeText).withZoneSameInstant(ZoneId.of(localZoneId));
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * Converts Local Date text to Local Date. The string must represent a valid date and is parsed using formatter DateTimeFormatter.ISO_LOCAL_DATE
     *
     * @param localDateText the local date (as text) to be converted e.g. "2023-12-05"
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDate convertToLocalDate(final String localDateText) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateText, ErrorMessages.LOCAL_DATE_TEXT_EMPTY));
        return LocalDate.parse(localDateText);
    }

    /**
     * Converts Local Date text to Local Date
     *
     * @param localDateText the local date (as text) to be converted e.g. "2023-12-05"
     * @param formatter     the formatter that holds the format pattern for the supplied local date
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDate convertToLocalDate(final String localDateText, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateText, ErrorMessages.LOCAL_DATE_TEXT_EMPTY), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        return LocalDate.parse(localDateText, formatter);
    }

    /**
     * Converts Local Date text to Local Date
     *
     * @param localDateText     the local date (as text) to be converted e.g. "05/12/2023"
     * @param dateFormatPattern the date format pattern for the supplied local date e.g. "dd/MM/yyyy"
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDate convertToLocalDate(final String localDateText, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateText, ErrorMessages.LOCAL_DATE_TEXT_EMPTY), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        return LocalDate.parse(localDateText, formatter);
    }

    /**
     * Converts Local Date Time text to Local Date Time. The string must represent a valid date and is parsed using formatter DateTimeFormatter.ISO_LOCAL_DATE_TIME
     *
     * @param localDateTimeText the local date time (as text) to be converted e.g. "2023-12-08T16:02:10"
     * @return the local date time obtained from the conversion.
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTime(final String localDateTimeText) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY));
        return LocalDateTime.parse(localDateTimeText);
    }

    /**
     * Converts Local Date Time text to Local Date Time
     *
     * @param localDateTimeText the local date time (as text) to be converted e.g. "18/11/2023T16:02:10"
     * @param formatter         the formatter that holds the format pattern for the supplied local date
     * @return the local date time obtained from the conversion.
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTime(final String localDateTimeText, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        return LocalDateTime.parse(localDateTimeText, formatter);
    }

    /**
     * Converts Local Date Time text to Local Date Time
     *
     * @param localDateTimeText the local date time (as text) to be converted e.g. "18/11/2023T16:02:10"
     * @param dateFormatPattern the date time format pattern for the supplied local date time e.g. "dd/MM/yyyy'T'HH:mm:ss"
     * @return the local date time obtained from the conversion.
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTimeWithPattern(final String localDateTimeText, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        return LocalDateTime.parse(localDateTimeText, formatter);
    }

    /**
     * Converts Local Date to Local Date Text. The local date is parsed using formatter DateTimeFormatter.ISO_LOCAL_DATE
     *
     * @param localDate the local date to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertToLocalDateText(final LocalDate localDate) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDate, ErrorMessages.LOCAL_DATE_NULL));
        return String.valueOf(localDate);
    }

    /**
     * Converts Local Date to Local Date Text
     *
     * @param localDate the local date to be converted
     * @param formatter the formatter that holds the format pattern that the local date is to be parsed
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertToLocalDateText(final LocalDate localDate, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDate, ErrorMessages.LOCAL_DATE_NULL), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        return localDate.format(formatter);
    }

    /**
     * Converts Local Date to Local Date Text
     *
     * @param localDate         the local date to be converted e.g. "05/12/2023"
     * @param dateFormatPattern the date format pattern for the supplied local date e.g. "dd/MM/yyyy"
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertToLocalDateText(final LocalDate localDate, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDate, ErrorMessages.LOCAL_DATE_NULL), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        return localDate.format(formatter);
    }

    /**
     * Converts Local Date Time to Local Date Time Text
     *
     * @param localDateTime the local date time to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertToLocalDateTimeText(final LocalDateTime localDateTime) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL));
        return String.valueOf(localDateTime);
    }

    /**
     * Converts Local Date Time to Local Date Time Text
     *
     * @param localDateTime the local date time to be converted
     * @param formatter     the formatter that holds the format pattern that the local date is to be parsed
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertToLocalDateTimeText(final LocalDateTime localDateTime, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        return localDateTime.format(formatter);
    }

    /**
     * Converts Local Date Time to Local Date Time Text
     *
     * @param localDateTime     the local date time to be converted e.g. "05/12/2023'T'12:02:01"
     * @param dateFormatPattern the date format pattern for the supplied local date time e.g. "yyyy-MM-dd HH:mm:ss"
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertToLocalDateTimeText(final LocalDateTime localDateTime, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        return localDateTime.format(formatter);
    }

    /**
     * Converts Timestamp to Local Date Time
     *
     * @param timestamp the local date time to be converted
     * @return the local date time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTime(final Timestamp timestamp) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(timestamp, ErrorMessages.TIMESTAMP_NULL));
        return timestamp.toLocalDateTime();
    }

    /**
     * Converts Local Date Time to Timestamp
     *
     * @param localDateTime the local date time to be converted
     * @return the timestamp obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static Timestamp convertToTimestamp(final LocalDateTime localDateTime) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL));
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Converts Local Date Time text to Timestamp
     *
     * @param localDateTimeText the local date time text to be converted
     * @return the timestamp obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static Timestamp convertToTimestamp(final String localDateTimeText) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY));
        LocalDateTime localDateTime = convertToLocalDateTime(localDateTimeText);
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Converts Epoch Millis to Local Date Time. The time zone defaults to UTC
     *
     * @param epochMillis the epoch millis to be converted
     * @return the local date time obtained from the conversion. The result will be in UTC time zone
     * @throws DateTimeException if the result exceeds the supported range or if the instant exceeds the maximum or minimum instant
     * @since 1.0.0
     */
    public static LocalDateTime convertEpochMillisToLocalDateTime(final long epochMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.of(ZONE_ID_UTC));
    }

    /**
     * Converts Epoch Millis to Local Date Time.
     *
     * @param epochMillis      the epoch millis to be converted
     * @param targetTimeZoneId the target time zone id to be applied to the resulting date time e.g. "Africa/Johannesburg"
     * @return the local date time obtained from the conversion. The result will be converted to the supplied target time zone
     * @throws DateTimeException if the result exceeds the supported range or if the instant exceeds the maximum or minimum instant
     * @since 1.0.0
     */
    public static LocalDateTime convertEpochMillisToLocalDateTime(final long epochMillis, String targetTimeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(targetTimeZoneId, ErrorMessages.TARGET_TIME_ZONE_ID_EMPTY));
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.of(targetTimeZoneId));
    }

    /**
     * Converts Local Date Time to Epoch Millis
     *
     * @param localDateTime the local date time to be converted
     * @param timeZoneId    the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the epoch millis time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if the result exceeds the supported range or if the instant exceeds the maximum or minimum instant
     * @throws ArithmeticException      if numeric overflow occur
     * @since 1.0.0
     */
    public static long convertToTimeEpochMillis(final LocalDateTime localDateTime, final String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        return localDateTime.atZone(ZoneId.of(timeZoneId)).withZoneSameInstant(ZoneId.of(timeZoneId)).toInstant().toEpochMilli();
    }

    private static DateTimeFormatter getDateFormatter(final String dateFormatPattern) {
        try {
            return DateTimeFormatter.ofPattern(dateFormatPattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format pattern supplied -> " + dateFormatPattern);
        }
    }

}
