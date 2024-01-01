package za.co.stridepace.dateutil.timezone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.stridepace.dateutil.model.TimeZoneDetail;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Perceive Chuchu
 */
@ExtendWith(MockitoExtension.class)
class TimeZoneUtilTests {

    @Test
    void getAllTimeZones_ReturnCorrectTimeZoneDetailsList_WhenGetAllTimeZonesIsInvoked() {
        List<TimeZoneDetail> timeZones = TimeZoneUtil.getAllTimeZones();
        assertNotNull(timeZones);
        // Verify that Time Zone with Zone Id (Africa/Johannesburg) exists in the list
        TimeZoneDetail timeZone = timeZones.stream().filter(timeZoneDetail -> timeZoneDetail.getId().equals("Africa/Johannesburg")).collect(Collectors.toList()).get(0);
        assertEquals(7200000, timeZone.getOffsetMillis());
        assertEquals("+02:00", timeZone.getOffsetText());
        assertEquals("SAST", timeZone.getAbbreviation());
        assertEquals("South Africa Standard Time", timeZone.getDisplayName());
    }

    @Test
    void isValidTimeZoneId_ReturnTrue_WhenTimeZoneIdIsValid() {
        TimeZoneUtil.getAllTimeZones();
        assertTrue(TimeZoneUtil.isValidTimeZoneId("Africa/Johannesburg"));
    }

}
