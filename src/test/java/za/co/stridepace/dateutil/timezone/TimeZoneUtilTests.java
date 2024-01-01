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
	void getAllTimeZones_ReturnCorrectTimeZoneDetailsList_WhenGetAllTimeZonesIsInvoked() {
		List<TimeZoneDetail> timeZones = TimeZoneUtil.getAllTimeZones();
		System.out.println(timeZones);
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

	@Test
	void deduceZoneOffset_ReturnCorrectZoneOffSet_WhenRawZoneOffsetIsValidPositiveValue() {
		String actualTimeZoneText = TimeZoneUtil.deduceZoneOffset(19800000);
		assertEquals("+05:30", actualTimeZoneText);
	}

	@Test
	void deduceZoneOffset_ReturnCorrectZoneOffSet_WhenRawZoneOffsetIsValidNegativeValue() {
		String actualTimeZoneText = TimeZoneUtil.deduceZoneOffset(-12600000);
		assertEquals("-03:30", actualTimeZoneText);
	}

	@Test
	void deduceZoneOffset_ReturnCorrectZoneOffSet_WhenRawZoneOffsetIsZero() {
		String actualTimeZoneText = TimeZoneUtil.deduceZoneOffset(0);
		assertEquals("00:00", actualTimeZoneText);
	}

}
