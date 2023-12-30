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
    void WhenValidLocalDateTimeIsSuppliedWithUTCZoneOffset_ShouldConvertToCorrectUTCZonedDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        String actualZonedDateTime = DateConverter.convertLocalDateTimeToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
        assertEquals("2023-11-10T10:00:00.254Z", actualZonedDateTime);
    }

    @Test
    void WhenValidUTCZoneDateTimeStringIsSupplied_ShouldConvertToCorrectLocalDateTime()  {
        LocalDateTime localDateTime = DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
        assertNotNull(localDateTime);
        assertEquals("2023-11-10T12:00:00.254", localDateTime.toString());
    }

    @Test
    void WhenUTCZoneDateTimeStringIsNotSupplied_ShouldThrowClientNoteExceptionBadRequest() {
        DateTimeParseException dateTimeParseException = assertThrows(DateTimeParseException.class, () -> DateConverter.convertUTCZonedDateTimeTextToLocalDateTime("2023-11-10T10:00:00.254", "+0200"));
        assertNotNull(dateTimeParseException);
    }
}
