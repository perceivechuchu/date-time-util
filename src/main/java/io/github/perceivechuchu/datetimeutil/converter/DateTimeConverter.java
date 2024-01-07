package io.github.perceivechuchu.datetimeutil.converter;

import io.github.perceivechuchu.datetimeutil.commons.model.ValidationEntry;
import io.github.perceivechuchu.datetimeutil.commons.util.ValidationUtil;
import io.github.perceivechuchu.datetimeutil.constant.ErrorMessages;
import io.github.perceivechuchu.datetimeutil.exception.DateTimeConversionException;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is a utility for date conversions
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class DateTimeConverter {

    /**
     * The zulu date format pattern
     */
    protected static final String YYYY_MM_DD_HH_MM_SS_SSSXXX = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    /**
     * The UTC zone id
     */
    private static final String ZONE_ID_UTC = "UTC";

    private DateTimeConverter() {
    }

    /**
     * Converts a LocalDateTime object to UTC ZonedDateTime in textual format
     *
     * @param localDateTime   the local date time to be converted
     * @param localTimeZoneId the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @return the local date time in textual format obtained from the conversion e.g. "2023-11-10T10:00:00.254Z"
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
     * Converts a LocalDateTime object to a UTC ZonedDateTime object
     *
     * @param localDateTime   the local date time to be converted
     * @param localTimeZoneId the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @return the zoned date time object obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static ZonedDateTime convertToUTCZonedDateTime(final LocalDateTime localDateTime, final String localTimeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(localTimeZoneId, ErrorMessages.LOCAL_TIME_ZONE_ID_EMPTY));
        try {
            return localDateTime.atZone(ZoneId.of(localTimeZoneId));
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a LocalDateTime object to a ZonedDateTime object
     *
     * @param localDateTime    the local date time to be converted
     * @param localTimeZoneId  the local time zone id for the supplied date time e.g. "Africa/Johannesburg"
     * @param targetTimeZoneId the target time zone id to be applied to the resulting zoned date time e.g. "US/Pacific"
     * @return the zoned date time object obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static ZonedDateTime convertToZonedDateTime(final LocalDateTime localDateTime, final String localTimeZoneId, final String targetTimeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(localTimeZoneId, ErrorMessages.LOCAL_TIME_ZONE_ID_EMPTY), ValidationEntry.getInstance(targetTimeZoneId, ErrorMessages.TARGET_TIME_ZONE_ID_EMPTY));
        try {
            return localDateTime.atZone(ZoneId.of(localTimeZoneId)).withZoneSameInstant(ZoneId.of(targetTimeZoneId));
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a UTC ZonedDateTime object (Zulu time) to LocalDateTime in textual format
     *
     * @param zonedDateTimeText the zoned date time in textual format to be converted e.g. "2023-11-10T10:00:00.254Z"
     * @param localZoneId       the local time zone id for the supplied zoned date time e.g. "Africa/Johannesburg"
     * @return the local date time object obtained from the conversion
     * @throws IllegalArgumentException    exception thrown when required parameter is missing
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTimeWithZone(final String zonedDateTimeText, final String localZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(zonedDateTimeText, ErrorMessages.ZONED_DATE_TIME_TEXT_EMPTY), ValidationEntry.getInstance(localZoneId, ErrorMessages.LOCAL_TIME_ZONE_ID_EMPTY));
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(zonedDateTimeText).withZoneSameInstant(ZoneId.of(localZoneId));
            return zonedDateTime.toLocalDateTime();
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts LocalDate in textual format with default date formatter to a LocalDate object. The string parameter must represent a valid date and is parsed using formatter DateTimeFormatter.ISO_LOCAL_DATE
     *
     * @param localDateText the local date in textual format to be converted e.g. "2023-12-05"
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDate convertToLocalDate(final String localDateText) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateText, ErrorMessages.LOCAL_DATE_TEXT_EMPTY));
        try {
            return LocalDate.parse(localDateText);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts LocalDate in textual format with supplied date formatter to a LocalDate object
     *
     * @param localDateText the local date in textual format to be converted e.g. "2023-12-05"
     * @param formatter     the formatter that holds the format pattern for the supplied local date
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDate convertToLocalDate(final String localDateText, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateText, ErrorMessages.LOCAL_DATE_TEXT_EMPTY), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        try {
            return LocalDate.parse(localDateText, formatter);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts LocalDate in textual format with supplied date format pattern to a LocalDate object
     *
     * @param localDateText     the local date in textual format to be converted e.g. "05/12/2023"
     * @param dateFormatPattern the date format pattern for the supplied local date e.g. "dd/MM/yyyy"
     * @return the local date obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDate convertToLocalDate(final String localDateText, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateText, ErrorMessages.LOCAL_DATE_TEXT_EMPTY), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        try {
            DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
            return LocalDate.parse(localDateText, formatter);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts LocalDateTime in textual format with default date formatter to a LocalDateTime object. The string must represent a valid date and is parsed using formatter DateTimeFormatter.ISO_LOCAL_DATE_TIME
     *
     * @param localDateTimeText the local date time in textual format to be converted e.g. "2023-12-08T16:02:10"
     * @return the local date time obtained from the conversion.
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTime(final String localDateTimeText) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY));
        try {
            return LocalDateTime.parse(localDateTimeText);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts LocalDateTime in textual format with supplied date formatter to a LocalDateTime object
     *
     * @param localDateTimeText the local date time in textual format to be converted e.g. "18/11/2023T16:02:10"
     * @param formatter         the formatter that holds the format pattern for the supplied local date
     * @return the local date time object obtained from the conversion.
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTime(final String localDateTimeText, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        try {
            return LocalDateTime.parse(localDateTimeText, formatter);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts LocalDateTime in textual format with supplied date format pattern to a LocalDateTime object
     *
     * @param localDateTimeText the local date time in textual format to be converted e.g. "18/11/2023T16:02:10"
     * @param dateFormatPattern the date time format pattern for the supplied local date time e.g. "dd/MM/yyyy'T'HH:mm:ss"
     * @return the local date time object obtained from the conversion.
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTimeWithPattern(final String localDateTimeText, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        try {
            DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
            return LocalDateTime.parse(localDateTimeText, formatter);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a LocalDate object with default date formatter to LocalDate in textual format.
     *
     * @param localDate the local date object to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static String convertToLocalDateText(final LocalDate localDate) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDate, ErrorMessages.LOCAL_DATE_NULL));
        return String.valueOf(localDate);
    }

    /**
     * Converts a LocalDate object with supplied date formatter to LocalDate in textual format
     *
     * @param localDate the local date object to be converted
     * @param formatter the formatter that holds the format pattern that the local date is to be parsed
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if an error occurs during printing
     * @since 1.0.0
     */
    public static String convertToLocalDateText(final LocalDate localDate, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDate, ErrorMessages.LOCAL_DATE_NULL), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        return localDate.format(formatter);
    }

    /**
     * Converts a LocalDate object with supplied date format pattern to LocalDate in textual format
     *
     * @param localDate         the local date object to be converted e.g. "05/12/2023"
     * @param dateFormatPattern the date format pattern for the supplied local date e.g. "dd/MM/yyyy"
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if an error occurs during printing
     * @since 1.0.0
     */
    public static String convertToLocalDateText(final LocalDate localDate, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDate, ErrorMessages.LOCAL_DATE_NULL), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        return localDate.format(formatter);
    }

    /**
     * Converts a LocalDateTime object with default date formatter to LocalDateTime in textual format
     *
     * @param localDateTime the local date time object to be converted
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static String convertToLocalDateTimeText(final LocalDateTime localDateTime) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL));
        return String.valueOf(localDateTime);
    }

    /**
     * Converts a LocalDateTime object with supplied date formatter to LocalDateTime in textual format
     *
     * @param localDateTime the local date time to be converted
     * @param formatter     the formatter that holds the format pattern that the local date is to be parsed
     * @return the local date text obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if an error occurs during printing
     * @since 1.0.0
     */
    public static String convertToLocalDateTimeText(final LocalDateTime localDateTime, final DateTimeFormatter formatter) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(formatter, ErrorMessages.DATE_FORMATTER_NULL));
        return localDateTime.format(formatter);
    }

    /**
     * Converts a LocalDateTime object with supplied date format pattern to LocalDateTime in textual format
     *
     * @param localDateTime     the local date time object to be converted
     * @param dateFormatPattern the date format pattern for the supplied local date time e.g. "dd/MM/yyyy'T'HH:mm:ss"
     * @return the local date text obtained from the conversion  e.g. "05/12/2023T16:02:10"
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if an error occurs during printing
     * @since 1.0.0
     */
    public static String convertToLocalDateTimeText(final LocalDateTime localDateTime, final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        DateTimeFormatter formatter = getDateFormatter(dateFormatPattern);
        return localDateTime.format(formatter);
    }

    /**
     * Converts a Timestamp object to a LocalDateTime object
     *
     * @param timestamp the timestamp object to be converted
     * @return the local date time object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static LocalDateTime convertToLocalDateTime(final Timestamp timestamp) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(timestamp, ErrorMessages.TIMESTAMP_NULL));
        return timestamp.toLocalDateTime();
    }

    /**
     * Converts a LocalDateTime object to a Timestamp object
     *
     * @param localDateTime the local date time object to be converted
     * @return the timestamp object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static Timestamp convertToTimestamp(final LocalDateTime localDateTime) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL));
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Converts LocalDateTime in textual format to a Timestamp object
     *
     * @param localDateTimeText the local date time text to be converted
     * @return the timestamp object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 1.0.0
     */
    public static Timestamp convertToTimestamp(final String localDateTimeText) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTimeText, ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY));
        LocalDateTime localDateTime = convertToLocalDateTime(localDateTimeText);
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Converts an Epoch time value in milliseconds with default zone (UTC) to a LocalDateTime object (Epoch is the number of seconds that have elapsed since 00:00:00 UTC on 1 January 1970). The time zone for the resulting LocalDateTime is defaulted to UTC
     *
     * @param epochMillis the epoch time value to be converted, in milliseconds
     * @return the local date time object obtained from the conversion. The result will be in UTC time zone
     * @since 1.0.0
     */
    public static LocalDateTime convertEpochMillisToLocalDateTime(final long epochMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.of(ZONE_ID_UTC));
    }

    /**
     * Converts an Epoch time value in milliseconds with supplied zone id to a LocalDateTime object
     *
     * @param epochMillis      the epoch time value to be converted, in milliseconds
     * @param targetTimeZoneId the target time zone id to be applied to the resulting date time e.g. "Africa/Johannesburg"
     * @return the local date time obtained from the conversion. The result will be converted to the supplied target time zone
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static LocalDateTime convertEpochMillisToLocalDateTime(final long epochMillis, String targetTimeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(targetTimeZoneId, ErrorMessages.TARGET_TIME_ZONE_ID_EMPTY));
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.of(targetTimeZoneId));
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a LocalDateTime object to Epoch time value in milliseconds
     *
     * @param localDateTime the local date time to be converted
     * @param timeZoneId    the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the epoch millis time value obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 1.0.0
     */
    public static long convertLocalDateTimeToEpochMillis(final LocalDateTime localDateTime, final String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        try {
            return localDateTime.atZone(ZoneId.of(timeZoneId)).withZoneSameInstant(ZoneId.of(timeZoneId)).toInstant().toEpochMilli();
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a date format pattern to a DateTimeFormatter object
     *
     * @param dateFormatPattern the date format pattern in textual format e.g. "yyyy-MM-dd HH:mm:ss"
     * @return the date time formatter object
     * @throws IllegalArgumentException if date format pattern is not valid
     * @since 1.0.0
     */
    protected static DateTimeFormatter getDateFormatter(final String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        return DateTimeFormatter.ofPattern(dateFormatPattern);
    }

    /**
     * Converts a java.util.Date object to a LocalDateTime object
     *
     * @param date the date object to be converted
     * @return the local date time object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @throws DateTimeException        if the result exceeds the supported range
     * @since 2.0.2
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(date, ErrorMessages.DATE_NULL));
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Converts a java.util.Date object to a LocalDateTime object
     *
     * @param date       the date object to be converted
     * @param timeZoneId the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the local date time object obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 2.0.2
     */
    public static LocalDateTime convertToLocalDateTime(Date date, String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(date, ErrorMessages.DATE_NULL), ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        try {
            ZoneId zoneId = ZoneId.of(timeZoneId);
            return LocalDateTime.ofInstant(date.toInstant(), zoneId);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a java.util.Calendar object with supplied time zone id to a LocalDateTime object
     *
     * @param calendar   the calendar object to be converted
     * @param timeZoneId the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the local date time object obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 2.0.2
     */
    public static LocalDateTime convertToLocalDateTime(Calendar calendar, String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(calendar, ErrorMessages.CALENDAR_NULL), ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        try {
            ZoneId zoneId = ZoneId.of(timeZoneId);
            return LocalDateTime.ofInstant(calendar.toInstant(), zoneId);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a java.util.Date object to a ZonedDateTime object
     *
     * @param date the date object to be converted
     * @return the zoned date time object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 2.0.2
     */
    public static ZonedDateTime convertToZonedDateTime(Date date) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(date, ErrorMessages.DATE_NULL));
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Converts a java.util.Calendar object to a ZonedDateTime object
     *
     * @param calendar the calendar object to be converted
     * @return the zoned date time object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 2.0.2
     */
    public static ZonedDateTime convertToZonedDateTime(Calendar calendar) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(calendar, ErrorMessages.CALENDAR_NULL));
        return ZonedDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Converts a LocalDateTime object with supplied time zone id to a ZonedDateTime object
     *
     * @param localDateTime the local date time to be converted
     * @param timeZoneId    the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the zoned date time object obtained from the conversion
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeConversionException if the date conversion fails
     * @since 2.0.2
     */
    public static ZonedDateTime convertToZonedDateTime(LocalDateTime localDateTime, String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        try {
            ZoneId zoneId = ZoneId.of(timeZoneId);
            return localDateTime.atZone(zoneId);
        } catch (Exception e) {
            throw new DateTimeConversionException(e.getMessage());
        }
    }

    /**
     * Converts a ZonedDateTime object to a Calendar object
     *
     * @param zonedDateTime the zoned date time to be converted
     * @return the calendar object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 2.0.2
     */
    public static Calendar convertToCalendar(ZonedDateTime zonedDateTime) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(zonedDateTime, ErrorMessages.ZONED_DATE_TIME_NULL));
        Date date = Date.from(zonedDateTime.toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Converts a ZonedDateTime object to a Calendar object
     *
     * @param localDateTime the local date time to be converted
     * @param timeZoneId    the time zone id to be used in the conversion e.g. "Africa/Johannesburg"
     * @return the calendar object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 2.0.2
     */
    public static Calendar convertToCalendar(LocalDateTime localDateTime, String timeZoneId) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(localDateTime, ErrorMessages.LOCAL_DATE_TIME_NULL), ValidationEntry.getInstance(timeZoneId, ErrorMessages.TIME_ZONE_ID_EMPTY));
        ZonedDateTime zonedDateTime = convertToZonedDateTime(localDateTime, timeZoneId);
        return convertToCalendar(zonedDateTime);
    }

    /**
     * Converts a java.util.Date object to a java.util.Calendar object
     *
     * @param date the date to be converted
     * @return the calendar object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 2.0.2
     */
    public static Calendar convertToCalendar(Date date) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(date, ErrorMessages.DATE_NULL));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Converts a java.util.Calendar object to a java.util.Date object
     *
     * @param calendar the calendar to be converted
     * @return the date object obtained from the conversion
     * @throws IllegalArgumentException if parameter is not valid
     * @since 2.0.2
     */
    public static Date convertToDate(Calendar calendar) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(calendar, ErrorMessages.CALENDAR_NULL));
        return calendar.getTime();
    }

}
