package za.co.stridepace.datetimeutil.constant;

/**
 * This class is a utility for error message constant variables
 *
 * @author Perceive Chuchu
 */
public class ErrorMessages {

    private ErrorMessages() {}

    /**
     * The error message for null localDateTime parameter validation
     */
    public static final String LOCAL_DATE_TIME_NULL = "Parameter localDateTime cannot be null";

    /**
     * The error message for null localDate parameter validation
     */
    public static final String LOCAL_DATE_NULL = "Parameter localDate cannot be null";

    /**
     * The error message for null localDateText parameter validation
     */
    public static final String LOCAL_DATE_TEXT_EMPTY = "Parameter localDateText cannot be null";

    /**
     * The error message for empty localDateTimeText parameter validation
     */
    public static final String LOCAL_DATE_TIME_TEXT_EMPTY = "Parameter localDateTimeText cannot be empty";

    /**
     * The error message for empty timeZoneId parameter validation
     */
    public static final String TIME_ZONE_ID_EMPTY = "Parameter timeZoneId cannot be empty";

    /**
     * The error message for empty invalid timeZoneId parameter validation
     */
    public static final String TIME_ZONE_ID_INVALID = "Parameter timeZoneId is invalid";

    /**
     * The error message for empty localZoneId parameter validation
     */
    public static final String LOCAL_TIME_ZONE_ID_EMPTY = "Parameter localZoneId cannot be empty";

    /**
     * The error message for empty targetTimeZoneId parameter validation
     */
    public static final String TARGET_TIME_ZONE_ID_EMPTY = "Parameter targetTimeZoneId cannot be empty";

    /**
     * The error message for empty zonedDateTimeText parameter validation
     */
    public static final String ZONED_DATE_TIME_TEXT_EMPTY = "Parameter zonedDateTimeText cannot be empty";

    /**
     * The error message for null formatter parameter validation
     */
    public static final String DATE_FORMATTER_NULL = "Parameter formatter cannot be null";

    /**
     * The error message for empty dateFormatPattern parameter validation
     */
    public static final String DATE_FORMAT_PATTERN_EMPTY = "Parameter dateFormatPattern cannot be empty";

    /**
     * The error message for null timestamp parameter validation
     */
    public static final String TIMESTAMP_NULL = "Parameter timestamp cannot be null";

    /**
     * The error message for empty offsetText parameter validation
     */
    public static final String TIME_OFFSET_TEXT_EMPTY = "Parameter offsetText cannot be empty";
}
