package za.co.stridepace.dateutil.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.stridepace.dateutil.exception.DateUtilException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateConverterTests {


    @Test
    void WhenValidLocalDateTimeIsSuppliedWithUTCZoneOffset_ShouldConvertToCorrectUTCZonedDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
        String actualZonedDateTime = DateConverter.convertLocalDateTimeToUTCZonedDateTimeString(localDateTime, "Africa/Johannesburg");
        assertEquals("2023-11-10T10:00:00.254Z", actualZonedDateTime);
    }

    @Test
    void WhenValidUTCZoneDateTimeStringIsSupplied_ShouldConvertToCorrectLocalDateTime() throws DateUtilException {
        LocalDateTime localDateTime = DateConverter.convertUTCZonedDateTimeStringToLocalDateTime("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
        assertNotNull(localDateTime);
        assertEquals("2023-11-10T12:00:00.254", localDateTime.toString());
    }

    @Test
    void WhenUTCZoneDateTimeStringIsNotSupplied_ShouldThrowClientNoteExceptionBadRequest() {
        DateUtilException dateUtilException = assertThrows(DateUtilException.class, () -> DateConverter.convertUTCZonedDateTimeStringToLocalDateTime("2023-11-10T10:00:00.254", "+0200"));
        assertNotNull(dateUtilException);
        assertEquals(400, dateUtilException.getCode());
    }
}
