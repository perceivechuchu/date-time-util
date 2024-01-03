package za.co.stridepace.datetimeutil.timezone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Perceive Chuchu
 */
@ExtendWith(MockitoExtension.class)
class ZoneOffsetUtilTests {

    @Test
    void deduceZoneOffset_ReturnCorrectZoneOffSet_WhenRawZoneOffsetIsValidPositiveValue() {
        String actualTimeZoneText = ZoneOffsetUtil.deduceZoneOffset(19800000);
        assertEquals("+05:30", actualTimeZoneText);
    }

    @Test
    void deduceZoneOffset_ReturnCorrectZoneOffSet_WhenRawZoneOffsetIsValidNegativeValue() {
        String actualTimeZoneText = ZoneOffsetUtil.deduceZoneOffset(-12600000);
        assertEquals("-03:30", actualTimeZoneText);
    }

    @Test
    void deduceZoneOffset_ReturnCorrectZoneOffSet_WhenRawZoneOffsetIsZero() {
        String actualTimeZoneText = ZoneOffsetUtil.deduceZoneOffset(0);
        assertEquals("00:00", actualTimeZoneText);
    }

}
