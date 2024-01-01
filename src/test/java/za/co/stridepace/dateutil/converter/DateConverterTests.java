package za.co.stridepace.dateutil.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateConverterTests {


    @Test
    void convertLocalDateTimeToUTCZonedDateTimeText_ReturnCorrectConvertedUTCZonedDateTime_WhenLocalDateTimeAndLocalZoneIdIsValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        String actualZonedDateTime = DateConverter.convertLocalDateTimeToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
        assertEquals("2023-11-10T10:00:00.254Z", actualZonedDateTime);
    }

    @Test
    void  convertUTCZonedDateTimeTextToLocalDateTime_ReturnCorrectConvertedLocalDateTime_WhenUTCZoneDateTimeTextIsValid() {
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
    void convertLocalDateTextToLocalDate_ReturnCorrectConvertedLocalDate_WhenLocalDateTextIsValid() {
        LocalDate actualLocalDate = DateConverter.convertLocalDateTextToLocalDate("2023-12-05");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertLocalDateTextToLocalDate_ThrowIllegalArgumentException_WhenLocalDateTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertLocalDateTextToLocalDate(""));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertLocalDateTextToLocalDate_ThrowDateTimeParseException_WhenLocalDateTextIsInvalid() {
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, () -> DateConverter.convertLocalDateTextToLocalDate("2023-50-05"));
        assertNotNull(dateTimeParseException);
    }

    @Test
    void convertLocalDateTextToLocalDate_ReturnCorrectConvertedLocalDate_WhenBothLocalDateTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate actualLocalDate = DateConverter.convertLocalDateTextToLocalDate("05/12/2023", formatter);
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertLocalDateTextToLocalDate_ReturnCorrectConvertedLocalDate_WhenBothLocalDateTextAndDateFormatPatternAreValid() {
        LocalDate actualLocalDate = DateConverter.convertLocalDateTextToLocalDate("05/12/2023", "dd/MM/yyyy");
        LocalDate expectedLocalDate = LocalDate.of(2023, 12, 5);
        assertTrue(actualLocalDate.isEqual(expectedLocalDate));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ReturnCorrectConvertedLocalDateTime_WhenLocalDateTimeTextIsValid() {
        LocalDateTime actualLocalDateTime = DateConverter.convertLocalDateTimeTextToLocalDateTime("2023-11-18T16:02:10");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);;
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ReturnCorrectConvertedLocalDateTime_WhenLocalDateTimeTextAndFormatterAreValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime actualLocalDateTime = DateConverter.convertLocalDateTimeTextToLocalDateTime("18/11/2023T16:02:10", formatter);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);;
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ReturnCorrectConvertedLocalDateTime_WhenLocalDateTimeTextAndDateFormatPatternAreValid() {
        LocalDateTime actualLocalDateTime = DateConverter.convertLocalDateTimeTextToLocalDateTime("18/11/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss");
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 18, 16, 2, 10);;
        assertTrue(actualLocalDateTime.isEqual(expectedLocalDateTime));
    }

    @Test
    void convertLocalDateTimeTextToLocalDateTime_ThrowIllegalArgumentException_WhenLocalDateTimeTextIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertLocalDateTimeTextToLocalDateTime(""));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertLocalDatTimeTextToLocalDateTime_ThrowDateTimeParseException_WhenLocalDateTimeTextIsInvalid() {
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, () -> DateConverter.convertLocalDateTimeTextToLocalDateTime("18/11/2023 16:02:10"));
        assertNotNull(dateTimeParseException);
    }

}
