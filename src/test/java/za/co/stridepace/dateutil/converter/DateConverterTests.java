package za.co.stridepace.dateutil.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.stridepace.dateutil.constant.ErrorMessages;
import za.co.stridepace.dateutil.exception.DateConversionException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Perceive Chuchu
 */
@ExtendWith(MockitoExtension.class)
class DateConverterTests {


    @Test
    void convertToUTCZonedDateTimeText_ReturnCorrectlyConvertedUTCZonedDateTime_WhenLocalDateTimeAndLocalZoneIdIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        String actualZonedDateTime = DateConverter.convertToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
        assertEquals("2023-11-10T10:00:00.254Z", actualZonedDateTime);
    }

    @Test
    void convertToUTCZonedDateTime_ReturnCorrectlyConvertedUTCZonedDateTime_WhenLocalDateTimeIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2023, 11, 10, 12, 0, 0, 254000000, ZoneId.of("Africa/Johannesburg"));
        ZonedDateTime actualZonedDateTime = DateConverter.convertToUTCZonedDateTime(localDateTime, "Africa/Johannesburg");
        assertTrue(expectedZonedDateTime.isEqual(actualZonedDateTime));
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowDateConversionException_WhenUTCZoneDateTimeIsInvalid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToUTCZonedDateTime(localDateTime, "invalid_timezone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToZonedDateTime_ReturnCorrectlyConvertedZonedDateTime_WhenLocalDateTimeAndTimeZoneIdsAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2023, 11, 10, 2, 0, 0, 254000000, ZoneId.of("US/Pacific"));
        // US/Pacific is behind Africa/Johannesburg with 10 hours
        ZonedDateTime actualZonedDateTime = DateConverter.convertToZonedDateTime(localDateTime, "Africa/Johannesburg", "US/Pacific");
        assertTrue(expectedZonedDateTime.isEqual(actualZonedDateTime));
    }

    @Test
    void convertToZonedDateTime_ThrowDateConversionException_WhenLocalTimeZoneIdIsInvalid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToZonedDateTime(localDateTime, "invalid_timezone", "US/Pacific"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTimeWithZone_ReturnCorrectlyConvertedLocalDateTime_WhenUTCZoneDateTimeTextIsValid() {
        LocalDateTime localDateTime = DateConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
        assertNotNull(localDateTime);
        assertEquals("2023-11-10T12:00:00.254", localDateTime.toString());
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowIllegalArgumentException_WhenUTCZoneDateTimeTextIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertToLocalDateTimeWithZone(null, "Africa/Johannesburg"));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowIllegalArgumentException_WhenLocalZoneIdIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254", ""));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertToLocalDateTimeWithZone_ThrowDateConversionException_WhenUTCZoneDateTimeTextIsInvalid() {
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254", "Africa/Johannesburg"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenLocalDateTextIsValid() {
        LocalDate actualLocalDate = DateConverter.convertToLocalDate("2023-12-05");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertToLocalDate_ThrowIllegalArgumentException_WhenLocalDateTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertToLocalDate(""));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TEXT_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDate_ThrowDateConversionException_WhenLocalDateTextIsInvalid() {
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToLocalDate("2023-50-05"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenBothLocalDateTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate actualLocalDate = DateConverter.convertToLocalDate("05/12/2023", formatter);
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertToLocalDate2_ThrowDateConversionException_WhenLocalDateTextIsInvalid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToLocalDate("2023-50-05", formatter));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenBothLocalDateTextAndDateFormatPatternAreValid() {
        LocalDate actualLocalDate = DateConverter.convertToLocalDate("05/12/2023", "dd/MM/yyyy");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertToLocalDate3_ThrowDateConversionException_WhenLocalDateTextIsInvalid() {
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToLocalDate("2023-50-05", "dd/MM/yyyy"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ReturnCorrectConvertedLocalDateTime_WhenLocalDateTimeTextIsValid() {
        LocalDateTime actualLocalDateTime = DateConverter.convertToLocalDateTime("2023-11-18T16:02:10");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenLocalDateTimeTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime actualLocalDateTime = DateConverter.convertToLocalDateTime("18/11/2023T16:02:10", formatter);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTime2_ThrowDateConversionException_WhenLocalDateTimeTextIsInvalid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToLocalDateTime("18/50/2023T16:02:10", formatter));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTimeWithPattern_ReturnCorrectlyConvertedLocalDateTime_WhenLocalDateTimeTextAndDateFormatPatternAreValid() {
        LocalDateTime actualLocalDateTime = DateConverter.convertToLocalDateTimeWithPattern("18/11/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertToLocalDateTimeWithPattern_ThrowDateConversionException_WhenLocalDateTimeTextIsInvalid() {
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertToLocalDateTimeWithPattern("18/50/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ThrowIllegalArgumentException_WhenLocalDateTimeTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertToLocalDateTime(""));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDateTime_ThrowDateConversionException_WhenLocalDateTimeTextIsInvalid() {
        DateConversionException dateTimeParseException = assertThrows(DateConversionException.class, () -> DateConverter.convertToLocalDateTime("18/11/2023 16:02:10"));
        assertNotNull(dateTimeParseException);
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenLocalDateIsValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateConverter.convertToLocalDateText(localDate);
        assertEquals("2023-12-05", actualLocalDateText);
    }

    @Test
    void convertToLocalDateText_ThrowIllegalArgumentException_WhenLocalDateIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertToLocalDateText(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_NULL, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateIsValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateConverter.convertToLocalDateText(localDate);
        assertEquals("2023-12-05", actualLocalDateText);
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateAndFormatterAreValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualLocalDateText = DateConverter.convertToLocalDateText(localDate, formatter);
        assertEquals("05/12/2023", actualLocalDateText);
    }

    @Test
    void convertToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateTextAndDateFormatPatternAreValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateConverter.convertToLocalDateText(localDate, "dd/MM/yyyy");
        assertEquals("05/12/2023", actualLocalDateText);
    }

    @Test
    void convertToLocalDateTimeText_ThrowIllegalArgumentException_WhenLocalDateTimeIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertToLocalDateTimeText(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TIME_NULL, illegalArgumentException.getMessage());
    }

    @Test
    void convertToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        String actualLocalDateTimeText = DateConverter.convertToLocalDateTimeText(localDateTime);
        assertEquals("2023-12-05T16:02:10", actualLocalDateTimeText);
    }

    @Test
    void convertToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeAndFormatterAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        String actualLocalDateText = DateConverter.convertToLocalDateTimeText(localDateTime, formatter);
        assertEquals("05/12/2023T16:02:10", actualLocalDateText);
    }

    @Test
    void convertToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeTextAndDateFormatPatternAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        String actualLocalDateText = DateConverter.convertToLocalDateTimeText(localDateTime, "dd/MM/yyyy'T'HH:mm:ss");
        assertEquals("05/12/2023T16:02:10", actualLocalDateText);
    }

    @Test
    void convertToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenTimestampIsValid() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 1, 16, 1, 23);
        Timestamp timestamp = Timestamp.valueOf("2024-01-01 16:01:23.0");
        LocalDateTime actualLocalDateTime = DateConverter.convertToLocalDateTime(timestamp);
        assertTrue(expectedLocalDateTime.isEqual(actualLocalDateTime));
    }

    @Test
    void convertToTimestamp_ReturnCorrectlyConvertedTimestamp_WhenValidLocalDateTimeIsSupplied() {
        Timestamp expectedTimestamp = Timestamp.valueOf("2024-01-01 16:01:23.0");
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 16, 1, 23);
        Timestamp actualTimestamp = DateConverter.convertToTimestamp(localDateTime);
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    void convertToTimestamp_ReturnCorrectlyConvertedTimestamp_WhenValidLocalDateTimeTextIsSupplied() {
        Timestamp expectedTimestamp = Timestamp.valueOf("2024-01-01 16:01:23.0");
        Timestamp actualTimestamp = DateConverter.convertToTimestamp("2024-01-01T16:01:23");
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    void convertEpochMillisToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenEpochMillisIsValid() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 1, 16, 1, 23);
        LocalDateTime actualLocalDateTime = DateConverter.convertEpochMillisToLocalDateTime(1704124883000L);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertEpochMillisToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenBothEpochMillisAndTargetTimeZoneIdAreValid() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2024, 1, 1, 18, 1, 23);
        LocalDateTime actualLocalDateTime = DateConverter.convertEpochMillisToLocalDateTime(1704124883000L, "Africa/Johannesburg");
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertEpochMillisToLocalDateTime_ThrowDateConversionException_WhenTimeZoneIdIsInvalid() {
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertEpochMillisToLocalDateTime(1704124883000L, "invalid_timezone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void convertLocalDateTimeToEpochTimeMillis_ReturnCorrectlyConvertedEpochMillis_WhenEpochMillisIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 18, 1, 23);
        long actualEpochMillis = DateConverter.convertLocalDateTimeToEpochMillis(localDateTime, "Africa/Johannesburg");
        assertEquals(1704124883000L, actualEpochMillis);
    }

    @Test
    void convertLocalDateTimeToEpochTimeMillis_ThrowDateConversionException_WhenTimeZoneIdIsInvalid() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 18, 1, 23);
        DateConversionException dateConversionException = assertThrows(DateConversionException.class, () -> DateConverter.convertLocalDateTimeToEpochMillis(localDateTime, "invalid_timezone_id"));
        assertNotNull(dateConversionException);
        assertNotNull(dateConversionException.getMessage());
    }

    @Test
    void getDateFormatter_ReturnDateFormatter_WhenValidDateFormatPatternIsSupplied() {
        DateTimeFormatter dateTimeFormatter = DateConverter.getDateFormatter("dd/MM/yyyy'T'HH:mm:ss");
        assertNotNull(dateTimeFormatter);
    }

    @Test
    void getDateFormatter_IllegalArgumentException_WhenInvalidDateFormatPatternIsSupplied() {
        assertThrows(IllegalArgumentException.class, () -> DateConverter.getDateFormatter("invalid pattern"));
    }

    @Test
    void getDateFormatter_IllegalArgumentException_WhenNullDateFormatPatternIsSupplied() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.getDateFormatter(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.DATE_FORMAT_PATTERN_EMPTY, illegalArgumentException.getMessage());
    }

}
