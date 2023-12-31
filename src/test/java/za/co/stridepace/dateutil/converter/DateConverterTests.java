package za.co.stridepace.dateutil.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateConverterTests {


    @Test
    void convertLocalDateTimeToUTCZonedDateTimeText_CorrectConvertedUTCZonedDateTime_LocalDateTimeAndLocalZoneIdValid() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        String actualZonedDateTime = DateConverter.convertLocalDateTimeToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
        assertEquals("2023-11-10T10:00:00.254Z", actualZonedDateTime);
    }

    @Test
    void  convertUTCZonedDateTimeTextToLocalDateTime_CorrectConvertedLocalDateTime_UTCZoneDateTimeTextValid() {
        LocalDateTime localDateTime = DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
        assertNotNull(localDateTime);
        assertEquals("2023-11-10T12:00:00.254", localDateTime.toString());
    }

    @Test
    void convertUTCZonedDateTimeTextToLocalDateTime_IllegalArgumentException_UTCZoneDateTimeTextNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertUTCZonedDateTimeTextToLocalDateTime(null, "Africa/Johannesburg"));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertUTCZonedDateTimeTextToLocalDateTime_IllegalArgumentException_localZoneIdEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254", ""));
        assertNotNull(illegalArgumentException);
    }

    @Test
    void convertUTCZonedDateTimeTextToLocalDateTime_DateTimeParseException_UTCZoneDateTimeTextInvalid() {
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, () -> DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254", "Africa/Johannesburg"));
        assertNotNull(dateTimeParseException);
    }

}
