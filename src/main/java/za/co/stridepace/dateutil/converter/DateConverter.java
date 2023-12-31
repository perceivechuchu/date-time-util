package za.co.stridepace.dateutil.converter;

import org.apache.commons.lang3.StringUtils;
import za.co.stridepace.dateutil.constant.DateFormatConstant;
import za.co.stridepace.dateutil.constant.ErrorMessages;
import za.co.stridepace.dateutil.constant.ZoneConstant;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimeZone;

/**
 * This class is a utility for date conversions
 *
 * @since 1.0.0
 */
public class DateConverter {

    private DateConverter() {
    }

    /**
     * Converts Local Date Time to UTC Zoned Date Time in text
     *
     * @param localDateTime the local date time to be converted.
     * @param localTimeZoneId   the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @return the local date time text obtained from the conversion.
     * @throws IllegalArgumentException exception thrown when required parameter is missing.
     * @since 1.0.0
     */
    public static String convertLocalDateTimeToUTCZonedDateTimeText(final LocalDateTime localDateTime, final String localTimeZoneId) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
        if (StringUtils.isBlank(localTimeZoneId)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_ZONE_ID_EMPTY);
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateFormatConstant.YYYY_MM_DD_HH_MM_SS_SSSXXX);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(localTimeZoneId));
        dateFormatter.format(zonedDateTime);
        return dateFormatter.format(zonedDateTime.withZoneSameInstant(ZoneId.of(ZoneConstant.ZONE_ID_UTC)));
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
    public static LocalDateTime convertUTCZonedDateTimeTextToLocalDateTime(final String zonedDateTimeText, final String localZoneId) {
        if (StringUtils.isBlank(zonedDateTimeText)) {
            throw new IllegalArgumentException(ErrorMessages.ZONED_DATE_TIME_TEXT);
        }
        if (StringUtils.isBlank(localZoneId)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_ZONE_ID_EMPTY);
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(zonedDateTimeText).withZoneSameInstant(ZoneId.of(localZoneId));
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * Converts Local Date text to Local Date
     *
     * @param localDateText the local date (as text) to be converted e.g. "2023-12-05"
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDate convertLocalDateTextToLocalDate(final String localDateText) {
        if (StringUtils.isBlank(localDateText)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TEXT_EMPTY);
        }
        return LocalDate.parse(localDateText);
    }

    /**
     * Converts Local Date text to Local Date
     *
     * @param localDateText the local date (as text) to be converted e.g. "2023-12-05"
     * @param formatter     the formatter to format the supplied local date
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDate convertLocalDateTextToLocalDate(final String localDateText, final DateTimeFormatter formatter) {
        if (StringUtils.isBlank(localDateText)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TEXT_EMPTY);
        }
        if (Objects.isNull(formatter)) {
            throw new IllegalArgumentException(ErrorMessages.DATE_FORMATTER_NULL);
        }
        return LocalDate.parse(localDateText, formatter);
    }

    /**
     * Converts Local Date Time text to Local Date Time
     *
     * @param localDateTimeText the local date time (as text) to be converted e.g. "2023-12-08T16:00:00"
     * @return the local date time obtained from the conversion.
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDateTime convertLocalDateTimeTextToLocalDateTime(final String localDateTimeText) {
        if (StringUtils.isBlank(localDateTimeText)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY);
        }
        return LocalDateTime.parse(localDateTimeText);
    }

    /**
     * Converts Local Date Time text to Local Date Time
     *
     * @param localDateTimeText the local date time (as text) to be converted e.g. "2023-12-08T16:00:00"
     * @param formatter         the formatter to format the supplied local date time
     * @return the local date time obtained from the conversion.
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static LocalDateTime convertLocalDateTimeTextToLocalDateTime(final String localDateTimeText, final DateTimeFormatter formatter) {
        if (StringUtils.isBlank(localDateTimeText)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY);
        }
        if (Objects.isNull(formatter)) {
            throw new IllegalArgumentException(ErrorMessages.DATE_FORMATTER_NULL);
        }
        return LocalDateTime.parse(localDateTimeText, formatter);
    }

    /**
     * Converts Local Date to Local Date Text
     *
     * @param localDate the local date to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertLocalDateToLocalDateText(final LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_NULL);
        }
        LocalDate newLocalDate = convertLocalDateTextToLocalDate(localDate.toString());
        return newLocalDate.toString();
    }

    /**
     * Converts Local Date to Local Date Text
     *
     * @param localDate the local date to be converted
     * @param formatter the formatter to format the supplied local date time
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertLocalDateToLocalDateText(final LocalDate localDate, final DateTimeFormatter formatter) {
        if (Objects.isNull(localDate)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_NULL);
        }
        if (Objects.isNull(formatter)) {
            throw new IllegalArgumentException(ErrorMessages.DATE_FORMATTER_NULL);
        }
        LocalDate newLocalDate = convertLocalDateTextToLocalDate(localDate.toString(), formatter);
        return newLocalDate.toString();
    }

    /**
     * Converts Local Date to Local Date Text
     *
     * @param localDate         the local date to be converted
     * @param dateFormatPattern the date format pattern for the supplied local date e.g. "yyyy-MM-dd HH:mm:ss"
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertLocalDateToLocalDateText(final LocalDate localDate, final String dateFormatPattern) {
        if (Objects.isNull(localDate)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_NULL);
        }
        if (StringUtils.isBlank(dateFormatPattern)) {
            throw new IllegalArgumentException(ErrorMessages.DATE_FORMAT_PATTERN_EMPTY);
        }
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        LocalDate newLocalDate = convertLocalDateTextToLocalDate(localDate.toString(), formatter);
        return newLocalDate.toString();
    }

    /**
     * Converts Local Date Time to Local Date Text
     *
     * @param localDateTime the local date time to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertLocalDateTimeToLocalDateText(final LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
        LocalDateTime newLocalDate = convertLocalDateTimeTextToLocalDateTime(localDateTime.toString());
        return newLocalDate.toString();
    }

    /**
     * Converts Local Date Time to Local Date Text
     *
     * @param localDateTime the local date time to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertLocalDateTimeToLocalDateText(final LocalDateTime localDateTime, final DateTimeFormatter formatter) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
        if (Objects.isNull(formatter)) {
            throw new IllegalArgumentException(ErrorMessages.DATE_FORMATTER_NULL);
        }
        LocalDateTime newLocalDate = convertLocalDateTimeTextToLocalDateTime(localDateTime.toString(), formatter);
        return newLocalDate.toString();
    }

    /**
     * Converts Local Date Time to Local Date Text
     *
     * @param localDateTime the local date time to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException                if parameter is not valid
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     * @since 1.0.0
     */
    public static String convertLocalDateTimeToLocalDateText(final LocalDateTime localDateTime, final String dateFormatPattern) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
        if (StringUtils.isBlank(dateFormatPattern)) {
            throw new IllegalArgumentException(ErrorMessages.DATE_FORMAT_PATTERN_EMPTY);
        }
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        LocalDateTime newLocalDate = convertLocalDateTimeTextToLocalDateTime(localDateTime.toString(), formatter);
        return newLocalDate.toString();
    }

    /**
     * Converts Timestamp to Local Date Time
     *
     * @param timestamp the local date time to be converted
     * @return the local date time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static LocalDateTime convertTimestampToLocalDateTime(final Timestamp timestamp) {
        if (Objects.isNull(timestamp)) {
            throw new IllegalArgumentException(ErrorMessages.TIMESTAMP_NULL);
        }
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
    public static Timestamp convertLocalDateTimeToTimestamp(final LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
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
    public static Timestamp convertLocalDateTimeTextToTimestamp(final String localDateTimeText) {
        if (StringUtils.isBlank(localDateTimeText)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY);
        }
        LocalDateTime localDateTime = convertLocalDateTimeTextToLocalDateTime(localDateTimeText);
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Converts Epoch Millis to Local Date Time
     *
     * @param epochMillis the epoch millis to be converted
     * @return the local date time obtained from the conversion
     * @throws DateTimeException if the result exceeds the supported range or if the instant exceeds the maximum or minimum instant
     * @since 1.0.0
     */
    public static LocalDateTime convertEpochMillisToLocalDateTime(final long epochMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), TimeZone.getDefault().toZoneId());
    }

    /**
     * Converts Local Date Time to Epoch Millis
     *
     * @param localDateTime the local date time to be converted
     * @param timeZoneId        the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the epoch millis time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if the result exceeds the supported range or if the instant exceeds the maximum or minimum instant
     * @throws ArithmeticException      if numeric overflow occur
     * @since 1.0.0
     */
    public static long convertLocalDateTimeEpochMillis(final LocalDateTime localDateTime, final String timeZoneId) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
        if (StringUtils.isBlank(timeZoneId)) {
            throw new IllegalArgumentException(ErrorMessages.ZONE_ID_EMPTY);
        }
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(timeZoneId)).withZoneSameInstant(ZoneId.of(timeZoneId));
        return zonedDateTime.toInstant().toEpochMilli();
    }

    /**
     * Converts Local Date Time to UTC Zoned Date Time
     *
     * @param localDateTime the local date time to be converted
     * @param localTimeZoneId   the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @return the zoned date time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if the result exceeds the supported range
     * @since 1.0.0
     */
    public static ZonedDateTime convertLocalDateTimeToUTCZonedDateTime(final LocalDateTime localDateTime, final String localTimeZoneId) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
        if (StringUtils.isBlank(localTimeZoneId)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_ZONE_ID_EMPTY);
        }
        return localDateTime.atZone(ZoneId.of(localTimeZoneId)).withZoneSameInstant(TimeZone.getDefault().toZoneId());
    }

    /**
     * Converts Local Date Time to Zoned Date Time
     *
     * @param localDateTime the local date time to be converted
     * @param localTimeZoneId   the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @param targetTimeZoneId   the target time zone id to be applied to the resulting zoned date time e.g. "Africa/Johannesburg"
     * @return the zoned date time obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if the result exceeds the supported range
     * @since 1.0.0
     */
    public static ZonedDateTime convertLocalDateTimeToZonedDateTime(final LocalDateTime localDateTime, final String localTimeZoneId, final String targetTimeZoneId) {
        if (Objects.isNull(localDateTime)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_DATE_TIME_NULL);
        }
        if (StringUtils.isBlank(localTimeZoneId)) {
            throw new IllegalArgumentException(ErrorMessages.LOCAL_ZONE_ID_EMPTY);
        }
        if (StringUtils.isBlank(targetTimeZoneId)) {
            throw new IllegalArgumentException(ErrorMessages.TARGET_ZONE_ID_EMPTY);
        }
        return localDateTime.atZone(ZoneId.of(localTimeZoneId)).withZoneSameInstant(ZoneId.of(targetTimeZoneId));
    }

    private static DateTimeFormatter getDateFormatter(final String dateFormatPattern) {
        try {
            return DateTimeFormatter.ofPattern(dateFormatPattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format pattern supplied -> " + dateFormatPattern);
        }
    }

}
