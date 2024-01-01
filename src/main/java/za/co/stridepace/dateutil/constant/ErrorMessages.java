package za.co.stridepace.dateutil.constant;

/**
 * This class is a utility for error message constant variables
 *
 * @author Perceive Chuchu
 */
public class ErrorMessages {

    private ErrorMessages() {}

    /**
     * The error message for localDateTime parameter validation
     */
    public static final String LOCAL_DATE_TIME_NULL = "Parameter localDateTime cannot be null";

    /**
     * The error message for localDate parameter validation
     */
    public static final String LOCAL_DATE_NULL = "Parameter localDate cannot be null";

    /**
     * The error message for localDateText parameter validation
     */
    public static final String LOCAL_DATE_TEXT_EMPTY = "Parameter localDateText cannot be null";

    /**
     * The error message for localDateTimeText parameter validation
     */
    public static final String LOCAL_DATE_TIME_TEXT_EMPTY = "Parameter localDateTimeText cannot be null";

    /**
     * The error message for timeZoneId parameter validation
     */
    public static final String TIME_ZONE_ID_EMPTY = "Parameter timeZoneId cannot be empty";

    /**
     * The error message for localZoneId parameter validation
     */
    public static final String LOCAL_TIME_ZONE_ID_EMPTY = "Parameter localZoneId cannot be empty";

    /**
     * The error message for targetTimeZoneId parameter validation
     */
    public static final String TARGET_TIME_ZONE_ID_EMPTY = "Parameter targetTimeZoneId cannot be empty";

    /**
     * The error message for zonedDateTimeText parameter validation
     */
    public static final String ZONED_DATE_TIME_TEXT = "Parameter zonedDateTimeText cannot be empty";

    /**
     * The error message for formatter parameter validation
     */
    public static final String DATE_FORMATTER_NULL = "Parameter formatter cannot be null";

    /**
     * The error message for dateFormatPattern parameter validation
     */
    public static final String DATE_FORMAT_PATTERN_EMPTY = "Parameter dateFormatPattern cannot be empty";

    /**
     * The error message for timestamp parameter validation
     */
    public static final String TIMESTAMP_NULL = "Parameter timestamp cannot be null";
}
