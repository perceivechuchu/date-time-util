package za.co.stridepace.datetimeutil.timezone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.stridepace.datetimeutil.constant.ErrorMessages;
import za.co.stridepace.datetimeutil.model.TimeZoneDetail;

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
        System.out.println(timeZone);
        assertEquals(7200000, timeZone.getOffsetMillis());
        assertEquals("+02:00", timeZone.getOffsetText());
        assertEquals("SAST", timeZone.getAbbreviation());
        assertEquals("South Africa Standard Time", timeZone.getDisplayName());
    }

    @Test
    void isValidTimeZoneId_ReturnTrue_WhenTimeZoneIdIsValid() {
        assertTrue(TimeZoneUtil.isValidTimeZoneId("Africa/Johannesburg"));
    }

    @Test
    void isValidTimeZoneId_ReturnFalse_WhenTimeZoneIdIsInvalid() {
        assertFalse(TimeZoneUtil.isValidTimeZoneId("invalid_timezone_id"));
    }

    @Test
    void isValidTimeZoneId_ReturnTrue_WhenTimeZoneIdIsGMT() {
        assertTrue(TimeZoneUtil.isValidTimeZoneId("GMT"));
    }

    @Test
    void getTimeZoneAbbreviation_ReturnTimeZoneAbbreviation_WhenValidTimeZoneValueIsReceived() {
        assertEquals("SAST", TimeZoneUtil.getTimeZoneAbbreviation("Africa/Johannesburg"));
    }

    @Test
    void etTimeZoneAbbreviation_ThrowIllegalArgumentException_WhenLocalDateTimeTextIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> TimeZoneUtil.getTimeZoneAbbreviation(null));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.TIME_ZONE_ID_EMPTY, illegalArgumentException.getMessage());
    }

    @Test
    void getTimeZoneAbbreviation_ThrowIllegalArgumentException_WhenLocalDateTimeTextIsInvalid() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> TimeZoneUtil.getTimeZoneAbbreviation("invalid_timezone_id"));
        assertNotNull(illegalArgumentException);
        assertEquals(ErrorMessages.TIME_ZONE_ID_INVALID, illegalArgumentException.getMessage());
    }

    @Test
    void searchByOffsetText_ReturnCorrectTimeZoneDetailsList_WhenValidOffsetTextIsReceived() {
        List<TimeZoneDetail> timeZoneDetails = TimeZoneUtil.searchTimeZonesByOffsetText("+02:00");
        assertFalse(timeZoneDetails.isEmpty());
        List<String> abbreviationList = timeZoneDetails.stream().map(TimeZoneDetail::getAbbreviation).collect(Collectors.toList());
        assertTrue(abbreviationList.contains("SAST"));
    }

}
