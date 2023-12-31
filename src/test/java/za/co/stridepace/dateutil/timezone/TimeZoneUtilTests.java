package za.co.stridepace.dateutil.timezone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.stridepace.dateutil.domain.TimeZoneDetail;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class TimeZoneUtilTests {

	@Test
	void getAllTimeZones_CorrectTimeZoneDetailsList_GetAllTimeZones() {
		List<TimeZoneDetail> timeZones = TimeZoneUtil.getAllTimeZones();
		assertNotNull(timeZones);
		// Verify that Time Zone with Zone Id (Africa/Johannesburg) exists in the list
		TimeZoneDetail timeZone = timeZones.stream().filter(timeZoneDetail -> timeZoneDetail.getId().equals("Africa/Johannesburg")).collect(Collectors.toList()).get(0);
		assertEquals(2, timeZone.getOffset());
		assertEquals("SAST", timeZone.getAbbreviation());
		assertEquals("South Africa Standard Time", timeZone.getDisplayName());
	}

	@Test
	void isValidTimeZoneId_True_TimeZoneIdValid() {
		TimeZoneUtil.getAllTimeZones();
		assertTrue(TimeZoneUtil.isValidTimeZoneId("Africa/Johannesburg"));
	}

}
