package io.github.perceivechuchu.datetimeutil.converter;

import io.github.perceivechuchu.datetimeutil.constant.ErrorMessages;
import io.github.perceivechuchu.datetimeutil.exception.DateTimeConversionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Perceive Chuchu
 */
@ExtendWith(MockitoExtension.class)
class DateTimeConverterTests {


    @Test
    void convertToUTCZonedDateTimeText_ReturnCorrectlyConvertedUTCZonedDateTime_WhenLocalDateTimeAndLocalZoneIdIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        String actualZonedDateTime = DateTimeConverter.convertToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
        assertEquals("2023-11-10T10:00:00.254Z", actualZonedDateTime);
    }

    @Test
    void convertToUTCZonedDateTime_ReturnCorrectlyConvertedUTCZonedDateTime_WhenLocalDateTimeIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2023, 11, 10, 12, 0, 0, 254000000, ZoneId.of("Africa/Johannesburg"));
        ZonedDateTime actualZonedDateTime = DateTimeConverter.convertToUTCZonedDateTime(localDateTime, "Africa/Johannesburg");
        assertTrue(expectedZonedDateTime.isEqual(actualZonedDateTime));
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowDateConversionException_WhenUTCZoneDateTimeIsInvalid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToUTCZonedDateTime(localDateTime, "invalid_timezone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToZonedDateTime_ReturnCorrectlyConvertedZonedDateTime_WhenLocalDateTimeAndTimeZoneIdsAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2023, 11, 10, 2, 0, 0, 254000000, ZoneId.of("US/Pacific"));
        // US/Pacific is behind Africa/Johannesburg with 10 hours
        ZonedDateTime actualZonedDateTime = DateTimeConverter.convertToZonedDateTime(localDateTime, "Africa/Johannesburg", "US/Pacific");
        assertTrue(expectedZonedDateTime.isEqual(actualZonedDateTime));
    }

    @Test
    void convertToZonedDateTime_ThrowDateConversionException_WhenLocalTimeZoneIdIsInvalid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToZonedDateTime(localDateTime, "invalid_timezone", "US/Pacific"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTimeWithZone_ReturnCorrectlyConvertedLocalDateTime_WhenUTCZoneDateTimeTextIsValid() {
        LocalDateTime localDateTime = DateTimeConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
        assertNotNull(localDateTime);
        assertEquals("2023-11-10T12:00:00.254", localDateTime.toString());
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowIllegalArgumentException_WhenUTCZoneDateTimeTextIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.convertToLocalDateTimeWithZone(null, "Africa/Johannesburg"));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowIllegalArgumentException_WhenLocalZoneIdIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254", ""));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowDateConversionException_WhenUTCZoneDateTimeTextIsInvalid() {
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254", "Africa/Johannesburg"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenLocalDateTextIsValid() {
        LocalDate actualLocalDate = DateTimeConverter.convertToLocalDate("2023-12-05");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertToLocalDate_ThrowIllegalArgumentException_WhenLocalDateTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.convertToLocalDate(""));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TEXT_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDate_ThrowDateConversionException_WhenLocalDateTextIsInvalid() {
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDate("2023-50-05"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenBothLocalDateTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate actualLocalDate = DateTimeConverter.convertToLocalDate("05/12/2023", formatter);
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertToLocalDate2_ThrowDateConversionException_WhenLocalDateTextIsInvalid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDate("2023-50-05", formatter));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenBothLocalDateTextAndDateFormatPatternAreValid() {
        LocalDate actualLocalDate = DateTimeConverter.convertToLocalDate("05/12/2023", "dd/MM/yyyy");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertToLocalDate3_ThrowDateConversionException_WhenLocalDateTextIsInvalid() {
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDate("2023-50-05", "dd/MM/yyyy"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ReturnCorrectConvertedLocalDateTime_WhenLocalDateTimeTextIsValid() {
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTime("2023-11-18T16:02:10");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenLocalDateTimeTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTime("18/11/2023T16:02:10", formatter);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTime2_ThrowDateConversionException_WhenLocalDateTimeTextIsInvalid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDateTime("18/50/2023T16:02:10", formatter));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTimeWithPattern_ReturnCorrectlyConvertedLocalDateTime_WhenLocalDateTimeTextAndDateFormatPatternAreValid() {
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTimeWithPattern("18/11/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTimeWithPattern_ThrowDateConversionException_WhenLocalDateTimeTextIsInvalid() {
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDateTimeWithPattern("18/50/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ThrowIllegalArgumentException_WhenLocalDateTimeTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.convertToLocalDateTime(""));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ThrowDateConversionException_WhenLocalDateTimeTextIsInvalid() {
        DateTimeConversionException dateTimeParseException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDateTime("18/11/2023 16:02:10"));
        assertNotNull(dateTimeParseException);
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenLocalDateIsValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateTimeConverter.convertToLocalDateText(localDate);
        assertEquals("2023-12-05", actualLocalDateText);
    }

    @Test
    void convertToLocalDateText_ThrowIllegalArgumentException_WhenLocalDateIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.convertToLocalDateText(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_NULL, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateIsValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateTimeConverter.convertToLocalDateText(localDate);
        assertEquals("2023-12-05", actualLocalDateText);
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateAndFormatterAreValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualLocalDateText = DateTimeConverter.convertToLocalDateText(localDate, formatter);
        assertEquals("05/12/2023", actualLocalDateText);
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateTextAndDateFormatPatternAreValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateTimeConverter.convertToLocalDateText(localDate, "dd/MM/yyyy");
        assertEquals("05/12/2023", actualLocalDateText);
    }

    @Test
    void convertToLocalDateTimeText_ThrowIllegalArgumentException_WhenLocalDateTimeIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.convertToLocalDateTimeText(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TIME_NULL, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        String actualLocalDateTimeText = DateTimeConverter.convertToLocalDateTimeText(localDateTime);
        assertEquals("2023-12-05T16:02:10", actualLocalDateTimeText);
    }

    @Test
    void convertToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeAndFormatterAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        String actualLocalDateText = DateTimeConverter.convertToLocalDateTimeText(localDateTime, formatter);
        assertEquals("05/12/2023T16:02:10", actualLocalDateText);
    }

    @Test
    void convertToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeTextAndDateFormatPatternAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        String actualLocalDateText = DateTimeConverter.convertToLocalDateTimeText(localDateTime, "dd/MM/yyyy'T'HH:mm:ss");
        assertEquals("05/12/2023T16:02:10", actualLocalDateText);
    }

    @Test
    void convertToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenTimestampIsValid() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 1, 16, 1, 23);
        Timestamp timestamp = Timestamp.valueOf("2024-01-01 16:01:23.0");
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTime(timestamp);
        assertTrue(expectedLocalDateTime.isEqual(actualLocalDateTime));
    }

    @Test
    void convertToTimestamp_ReturnCorrectlyConvertedTimestamp_WhenValidLocalDateTimeIsSupplied() {
        Timestamp expectedTimestamp = Timestamp.valueOf("2024-01-01 16:01:23.0");
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 16, 1, 23);
        Timestamp actualTimestamp = DateTimeConverter.convertToTimestamp(localDateTime);
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    void convertToTimestamp_ReturnCorrectlyConvertedTimestamp_WhenValidLocalDateTimeTextIsSupplied() {
        Timestamp expectedTimestamp = Timestamp.valueOf("2024-01-01 16:01:23.0");
        Timestamp actualTimestamp = DateTimeConverter.convertToTimestamp("2024-01-01T16:01:23");
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    void convertEpochMillisToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenEpochMillisIsValid() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 1, 16, 1, 23);
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertEpochMillisToLocalDateTime(1704124883000L);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertEpochMillisToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenBothEpochMillisAndTargetTimeZoneIdAreValid() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 1, 18, 1, 23);
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertEpochMillisToLocalDateTime(1704124883000L, "Africa/Johannesburg");
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertEpochMillisToLocalDateTime_ThrowDateConversionException_WhenTimeZoneIdIsInvalid() {
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertEpochMillisToLocalDateTime(1704124883000L, "invalid_timezone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertLocalDateTimeToEpochTimeMillis_ReturnCorrectlyConvertedEpochMillis_WhenEpochMillisIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 18, 1, 23);
        long actualEpochMillis = DateTimeConverter.convertLocalDateTimeToEpochMillis(localDateTime, "Africa/Johannesburg");
        assertEquals(1704124883000L, actualEpochMillis);
    }

    @Test
    void convertLocalDateTimeToEpochTimeMillis_ThrowDateConversionException_WhenTimeZoneIdIsInvalid() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 18, 1, 23);
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertLocalDateTimeToEpochMillis(localDateTime, "invalid_timezone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void getDateFormatter_ReturnDateFormatter_WhenValidDateFormatPatternIsSupplied() {
        DateTimeFormatter dateTimeFormatter = DateTimeConverter.getDateFormatter("dd/MM/yyyy'T'HH:mm:ss");
        assertNotNull(dateTimeFormatter);
    }

    @Test
    void getDateFormatter_IllegalArgumentException_WhenInvalidDateFormatPatternIsSupplied() {
        assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.getDateFormatter("invalid pattern"));
    }

    @Test
    void getDateFormatter_IllegalArgumentException_WhenNullDateFormatPatternIsSupplied() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.getDateFormatter(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.DATE_FORMAT_PATTERN_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ReturnLocalDateTime_WhenDateIsSupplied() {
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTime(new Date(1704124883000L));
        assertNotNull(actualLocalDateTime);
    }

    @Test
    void convertToLocalDateTime_ReturnLocalDateTime_WhenDateAndTimeZoneIdAreSupplied() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 2, 1, 1, 23);
        // 1704124883000L = 2024-01-01T18:01:23 +02:00 SAST
        // "Asia/Tokyo" = +09:00 JST
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTime(new Date(1704124883000L), "Asia/Tokyo");
        assertNotNull(actualLocalDateTime);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTime_ThrowDateTimeConversionException_WhenTimeZoneIdSuppliedIsInvalid() {
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDateTime(new Date(1704124883000L), "invalid_time_zone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ReturnLocalDateTime_WhenCalendarAndTimeZoneIdAreSupplied() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 2, 1, 1, 23);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 23);
        calendar.set(Calendar.MILLISECOND, 0);

        // "Asia/Tokyo" = +09:00 JST
        LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTime(calendar, "Asia/Tokyo");
        assertNotNull(actualLocalDateTime);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTime_ThrowsDateTimeConversionException_WhenValidCalendarIsAndTimeZoneIdSuppliedIsInvalid() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 23);
        calendar.set(Calendar.MILLISECOND, 0);

        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToLocalDateTime(calendar, "invalid_time_zone_id"));

        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToZonedDateTime_ReturnZonedDateTime_WhenDateIsSupplied() {
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2024, 1, 1, 18, 1, 23, 0, ZoneId.systemDefault());
        // 1704124883000L = 2024-01-01T18:01:23 +02:00 SAST
        ZonedDateTime actualZonedDateTime = DateTimeConverter.convertToZonedDateTime(new Date(1704124883000L));
        assertNotNull(actualZonedDateTime);
        assertTrue(actualZonedDateTime.isEqual(expectedZonedDateTime));
    }

    @Test
    void convertToCalendar_ReturnCalendar_WhenZonedDateTimeIsSupplied() {
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.set(Calendar.YEAR, 2024);
        expectedCalendar.set(Calendar.MONTH, Calendar.JANUARY);
        expectedCalendar.set(Calendar.DAY_OF_MONTH, 1);
        expectedCalendar.set(Calendar.HOUR_OF_DAY, 18);
        expectedCalendar.set(Calendar.MINUTE, 1);
        expectedCalendar.set(Calendar.SECOND, 23);
        expectedCalendar.set(Calendar.MILLISECOND, 0);

        Calendar actualCalendar = DateTimeConverter.convertToCalendar(new Date(1704124883000L));
        assertNotNull(actualCalendar);
        assertEquals(expectedCalendar, actualCalendar);
    }

    @Test
    void convertToZonedDateTime_ReturnZonedDateTime_WhenCalendarIsSupplied() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 23);
        calendar.set(Calendar.MILLISECOND, 0);

        ZonedDateTime zonedDateTime = DateTimeConverter.convertToZonedDateTime(calendar);
        assertNotNull(zonedDateTime);
        assertEquals(2024, zonedDateTime.getYear());
        assertEquals(Month.JANUARY, zonedDateTime.getMonth());
        assertEquals(1, zonedDateTime.getDayOfMonth());
        assertEquals(18, zonedDateTime.getHour());
        assertEquals(1, zonedDateTime.getMinute());
        assertEquals(23, zonedDateTime.getSecond());
    }

    @Test
    void convertToZonedDateTime_ReturnZonedDateTime_WhenLocalDateAndTimeZoneAreSupplied() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 2, 1, 1, 23);
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2024, 1, 2, 1, 1, 23, 0, ZoneId.of("Africa/Johannesburg"));
        ZonedDateTime actualZonedDateTime = DateTimeConverter.convertToZonedDateTime(localDateTime, "Africa/Johannesburg");
        assertNotNull(actualZonedDateTime);
        assertTrue(actualZonedDateTime.isEqual(expectedZonedDateTime));
    }

    @Test
    void convertToZonedDateTime_ThrowDateTimeConversionException_WhenTimeZoneIdSuppliedIsInvalid() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 2, 1, 1, 23);
        DateTimeConversionException dateConversionException = assertThrows(DateTimeConversionException.class, () -> DateTimeConverter.convertToZonedDateTime(localDateTime, "invalid_time_zone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertZonedDateTimeToCalendar_ReturnCalendar_WhenZonedDateTimeIsSupplied() {
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.set(Calendar.YEAR, 2024);
        expectedCalendar.set(Calendar.MONTH, Calendar.JANUARY);
        expectedCalendar.set(Calendar.DAY_OF_MONTH, 2);
        expectedCalendar.set(Calendar.HOUR_OF_DAY, 1);
        expectedCalendar.set(Calendar.MINUTE, 1);
        expectedCalendar.set(Calendar.SECOND, 23);
        expectedCalendar.set(Calendar.MILLISECOND, 0);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 1, 2, 1, 1, 23, 0, ZoneId.of("Africa/Johannesburg"));
        Calendar actualCalendar = DateTimeConverter.convertToCalendar(zonedDateTime);
        assertNotNull(actualCalendar);
        assertEquals(expectedCalendar, actualCalendar);
    }

    @Test
    void convertZonedDateTimeToCalendar_ReturnCalendar_WhenZonedDateTimeAndTimeZoneIdAreSupplied() {
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.set(Calendar.YEAR, 2024);
        expectedCalendar.set(Calendar.MONTH, Calendar.JANUARY);
        expectedCalendar.set(Calendar.DAY_OF_MONTH, 2);
        expectedCalendar.set(Calendar.HOUR_OF_DAY, 1);
        expectedCalendar.set(Calendar.MINUTE, 1);
        expectedCalendar.set(Calendar.SECOND, 23);
        expectedCalendar.set(Calendar.MILLISECOND, 0);

        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 2, 1, 1, 23);
        Calendar actualCalendar = DateTimeConverter.convertToCalendar(localDateTime, "Africa/Johannesburg");
        assertNotNull(actualCalendar);
        assertEquals(expectedCalendar, actualCalendar);
    }

    @Test
    void convertToDate_ReturnDate_WhenCalendarIsSupplied() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 1, 2, 1, 1, 23, 0, ZoneId.of("Africa/Johannesburg"));
        Date expectedDate = Date.from(zonedDateTime.toInstant());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 23);
        calendar.set(Calendar.MILLISECOND, 0);

        Date actualDate = DateTimeConverter.convertToDate(calendar);
        assertNotNull(actualDate);
        assertEquals(expectedDate, actualDate);
    }

}
