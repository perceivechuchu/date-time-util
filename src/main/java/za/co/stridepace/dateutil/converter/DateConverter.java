package za.co.stridepace.dateutil.converter;

import org.apache.commons.lang3.StringUtils;
import za.co.stridepace.dateutil.constant.DateFormatConstant;
import za.co.stridepace.dateutil.constant.ResponseCodes;
import za.co.stridepace.dateutil.constant.ZoneConstant;
import za.co.stridepace.dateutil.exception.DateUtilException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateConverter {

    private DateConverter(){}

    public static String convertLocalDateTimeToUTCZonedDateTimeString(LocalDateTime localDateTime, String localZoneId) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateFormatConstant.YYYY_MM_DD_HH_MM_SS_SSSXXX);
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.of(localZoneId));
        dateFormatter.format(zdt);
        return dateFormatter.format(zdt.withZoneSameInstant(ZoneId.of(ZoneConstant.ZONE_ID_UTC)));
    }

    public static LocalDateTime convertUTCZonedDateTimeStringToLocalDateTime(String zonedDateTimeString, String localZoneOffset) throws DateUtilException {
        if (StringUtils.isEmpty(zonedDateTimeString)) {
            return null;
        }
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(zonedDateTimeString).withZoneSameInstant(ZoneId.of(localZoneOffset));
            return zonedDateTime.toLocalDateTime();
        } catch (Exception e) {
            throw new DateUtilException(ResponseCodes.BAD_REQUEST_CODE, "Invalid date time provided");
        }
    }

}
