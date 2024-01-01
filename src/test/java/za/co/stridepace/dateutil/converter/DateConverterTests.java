package za.co.stridepace.dateutil.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.stridepace.dateutil.constant.ErrorMessages;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateConverterTests {


    @Test
    void convertLocalDateTimeToUTCZonedDateTimeText_ReturnCorrectlyConvertedUTCZonedDateTime_WhenLocalDateTimeAndLocalZoneIdIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        String actualZonedDateTime = DateConverter.convertLocalDateTimeToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
        assertEquals("2023-11-10T10:00:00.254Z", actualZonedDateTime);
    }

    @Test
    void convertLocalDateTimeToUTCZonedDateTime_ReturnCorrectlyConvertedUTCZonedDateTime_WhenLocalDateTimeIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2023, 11, 10, 12, 0, 0, 254000000, ZoneId.of("Africa/Johannesburg"));
        ZonedDateTime actualZonedDateTime = DateConverter.convertLocalDateTimeToUTCZonedDateTime(localDateTime, "Africa/Johannesburg");
        assertTrue(expectedZonedDateTime.isEqual(actualZonedDateTime));
    }

    @Test
    void convertLocalDateTimeToZonedDateTime_ReturnCorrectlyConvertedZonedDateTime_WhenLocalDateTimeAndTimeZoneIdsAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        ZonedDateTime expectedZonedDateTime = ZonedDateTime.of(2023, 11, 10, 2, 0, 0, 254000000, ZoneId.of("US/Pacific"));
        // US/Pacific is behind Africa/Johannesburg with 10 hours
        ZonedDateTime actualZonedDateTime = DateConverter.convertLocalDateTimeToZonedDateTime(localDateTime, "Africa/Johannesburg", "US/Pacific");
        assertTrue(expectedZonedDateTime.isEqual(actualZonedDateTime));
    }

    @Test
    void  convertUTCZonedDateTimeTextToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenUTCZoneDateTimeTextIsValid() {
        LocalDateTime localDateTime = DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
        assertNotNull(localDateTime);
        assertEquals("2023-11-10T12:00:00.254", localDateTime.toString());
    }

    @Test
    void convertUTCZonedDateTimeTextToLocalDateTime_ThrowIllegalArgumentException_WhenUTCZoneDateTimeTextIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertUTCZonedDateTimeTextToLocalDateTime(null, "Africa/Johannesburg"));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertUTCZonedDateTimeTextToLocalDateTime_ThrowIllegalArgumentException_WhenLocalZoneIdIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254", ""));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertUTCZonedDateTimeTextToLocalDateTime_ThrowDateTimeParseException_WhenUTCZoneDateTimeTextIsInvalid() {
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, () -> DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254", "Africa/Johannesburg"));
        assertNotNull(dateTimeParseException);
    }

    @Test
    void convertLocalDateTextToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenLocalDateTextIsValid() {
        LocalDate actualLocalDate = DateConverter.convertLocalDateTextToLocalDate("2023-12-05");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertLocalDateTextToLocalDate_ThrowIllegalArgumentException_WhenLocalDateTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertLocalDateTextToLocalDate(""));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TEXT_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void convertLocalDateTextToLocalDate_ThrowDateTimeParseException_WhenLocalDateTextIsInvalid() {
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, () -> DateConverter.convertLocalDateTextToLocalDate("2023-50-05"));
        assertNotNull(dateTimeParseException);
    }

    @Test
    void convertLocalDateTextToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenBothLocalDateTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate actualLocalDate = DateConverter.convertLocalDateTextToLocalDate("05/12/2023", formatter);
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertLocalDateTextToLocalDate_ReturnCorrectlyConvertedLocalDate_WhenBothLocalDateTextAndDateFormatPatternAreValid() {
        LocalDate actualLocalDate = DateConverter.convertLocalDateTextToLocalDate("05/12/2023", "dd/MM/yyyy");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ReturnCorrectConvertedLocalDateTime_WhenLocalDateTimeTextIsValid() {
        LocalDateTime actualLocalDateTime = DateConverter.convertLocalDateTimeTextToLocalDateTime("2023-11-18T16:02:10");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenLocalDateTimeTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime actualLocalDateTime = DateConverter.convertLocalDateTimeTextToLocalDateTime("18/11/2023T16:02:10", formatter);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ReturnCorrectlyConvertedLocalDateTime_WhenLocalDateTimeTextAndDateFormatPatternAreValid() {
        LocalDateTime actualLocalDateTime = DateConverter.convertLocalDateTimeTextToLocalDateTime("18/11/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ThrowIllegalArgumentException_WhenLocalDateTimeTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertLocalDateTimeTextToLocalDateTime(""));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TIME_TEXT_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void convertLocalDatTimeTextToLocalDateTime_ThrowDateTimeParseException_WhenLocalDateTimeTextIsInvalid() {
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, () -> DateConverter.convertLocalDateTimeTextToLocalDateTime("18/11/2023 16:02:10"));
        assertNotNull(dateTimeParseException);
    }

    @Test
    void convertLocalDateToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenLocalDateIsValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateConverter.convertLocalDateToLocalDateText(localDate);
        assertEquals("2023-12-05", actualLocalDateText);
    }

    @Test
    void convertLocalDateToLocalDateText_ThrowIllegalArgumentException_WhenLocalDateIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertLocalDateToLocalDateText(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_NULL, illegalArgumentException.getMessage());
    }

    @Test
    void convertLocalDateToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateIsValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateConverter.convertLocalDateToLocalDateText(localDate);
        assertEquals("2023-12-05", actualLocalDateText);
    }

    @Test
    void convertLocalDateToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateAndFormatterAreValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualLocalDateText = DateConverter.convertLocalDateToLocalDateText(localDate, formatter);
        assertEquals("05/12/2023", actualLocalDateText);
    }

    @Test
    void convertLocalDateToLocalDateText_ReturnCorrectlyConvertedLocalDateText_WhenBothLocalDateTextAndDateFormatPatternAreValid() {
        LocalDate localDate = LocalDate.of(2023, 12, 5);
        String actualLocalDateText = DateConverter.convertLocalDateToLocalDateText(localDate, "dd/MM/yyyy");
        assertEquals("05/12/2023", actualLocalDateText);
    }

    @Test
    void convertLocalDateTimeToLocalDateTimeText_ThrowIllegalArgumentException_WhenLocalDateTimeIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertLocalDateTimeToLocalDateTimeText(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.LOCAL_DATE_TIME_NULL, illegalArgumentException.getMessage());
    }

    @Test
    void convertLocalDateTimeToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        String actualLocalDateTimeText = DateConverter.convertLocalDateTimeToLocalDateTimeText(localDateTime);
        assertEquals("2023-12-05T16:02:10", actualLocalDateTimeText);
    }

    @Test
    void convertLocalDateTimeToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeAndFormatterAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        String actualLocalDateText = DateConverter.convertLocalDateTimeToLocalDateTimeText(localDateTime, formatter);
        assertEquals("05/12/2023T16:02:10", actualLocalDateText);
    }

    @Test
    void convertLocalDateTimeToLocalDateTimeText_ReturnCorrectlyConvertedLocalDateTimeText_WhenBothLocalDateTimeTextAndDateFormatPatternAreValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
        String actualLocalDateText = DateConverter.convertLocalDateTimeToLocalDateTimeText(localDateTime, "dd/MM/yyyy'T'HH:mm:ss");
        assertEquals("05/12/2023T16:02:10", actualLocalDateText);
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
    void convertLocalDateTimeEpochMillis_ReturnCorrectlyConvertedEpochMillis_WhenEpochMillisIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 18, 1, 23);
        long actualEpochMillis = DateConverter.convertLocalDateTimeEpochMillis(localDateTime, "Africa/Johannesburg");
        assertEquals(1704124883000L, actualEpochMillis);
    }

}
