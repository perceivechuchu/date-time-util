package za.co.stridepace.dateutil.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

}
