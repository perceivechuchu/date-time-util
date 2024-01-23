package io.github.perceivechuchu.datetimeutil.validator;

import io.github.perceivechuchu.datetimeutil.commons.model.ValidationEntry;
import io.github.perceivechuchu.datetimeutil.commons.util.ValidationUtil;
import io.github.perceivechuchu.datetimeutil.constant.ErrorMessages;
import io.github.perceivechuchu.datetimeutil.exception.DateTimeValidationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class is a utility for date time validations
 *
 * @author Perceive Chuchu
 * @since 2.1.0
 */
@SuppressWarnings("unused")
public class DateTimeValidator {

    /**
     * The location for the file that contains date format patterns
     *
     * @since 2.1.0
     */
    private static final String FILE_LOCATION = "datetime/formats/all-formats.txt";

    /**
     * The holder for all the date formats list items
     *
     * @since 2.1.0
     */
    private static List<String> dateFormats;

    private DateTimeValidator() {
    }

    /**
     * Validates datetime in textual format. This uses the default local.
     *
     * @param dateTimeText the date time text to be validated
     * @return the boolean flag to indicate validity of the supplied datetime text
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeValidationException thrown when datetime validation fails.
     * @since 2.1.0
     */
    public static boolean isValid(String dateTimeText) {
        return isValid(dateTimeText, Locale.getDefault());
    }

    /**
     * Validates datetime in textual format with the locale
     *
     * @param dateTimeText the date time text to be validated
     * @param locale       the locale for the dateTimeText value
     * @return the boolean flag to indicate validity of the supplied datetime text
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeValidationException thrown when datetime validation fails.
     * @since 2.1.0
     */
    public static boolean isValid(String dateTimeText, Locale locale) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(dateTimeText, ErrorMessages.DATE_TIME_TEXT_EMPTY), ValidationEntry.getInstance(locale, ErrorMessages.LOCALE_NULL));
        try {
            loadDateFormats();
            DateUtils.parseDateStrictly(dateTimeText, locale, dateFormats.toArray(new String[0]));
            return true;
        } catch (ParseException e) {
            return false;
        } catch (Exception e) {
            throw new DateTimeValidationException("Failed to validate datetime -> " + dateTimeText);
        }
    }

    /**
     * Validates datetime in textual format with the date format pattern to validate against
     *
     * @param dateTimeText      the date time text to be validated
     * @param dateFormatPattern the date format pattern to validate against
     * @return the boolean flag to indicate validity of the supplied datetime text
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeValidationException if the datetime validation fails.
     * @since 2.1.0
     */
    public static boolean isValid(String dateTimeText, String dateFormatPattern) {
        return isValid(dateTimeText, Locale.getDefault(), dateFormatPattern);
    }

    /**
     * Validates datetime in textual format with locale and the date format pattern to validate against
     *
     * @param dateTimeText      the date time text to be validated
     * @param locale            the locale for the dateTimeText value
     * @param dateFormatPattern the date format pattern to validate against
     * @return the boolean flag to indicate validity of the supplied datetime text
     * @throws IllegalArgumentException    if parameter is not valid
     * @throws DateTimeValidationException if the datetime validation fails.
     * @since 2.1.0
     */
    public static boolean isValid(String dateTimeText, Locale locale, String dateFormatPattern) {
        ValidationUtil.rejectEmpty(ValidationEntry.getInstance(dateTimeText, ErrorMessages.DATE_TIME_TEXT_EMPTY), ValidationEntry.getInstance(locale, ErrorMessages.LOCALE_NULL), ValidationEntry.getInstance(dateFormatPattern, ErrorMessages.DATE_FORMAT_PATTERN_EMPTY));
        try {
            loadDateFormats();
            DateUtils.parseDateStrictly(dateTimeText, locale, dateFormatPattern);
            return true;
        } catch (ParseException e) {
            return false;
        } catch (Exception e) {
            throw new DateTimeValidationException("Failed to validate datetime -> " + dateTimeText);
        }
    }

    /**
     * Validates the date format pattern contained in a file
     *
     * @param dateFormatPattern the date format pattern contained in the file
     * @return the boolean flag to indicate validity of the supplied date format pattern text
     * @since 2.1.0
     */
    private static boolean isValidDateFormatPattern(String dateFormatPattern) {
        return StringUtils.isNotBlank(dateFormatPattern) && !dateFormatPattern.contains("_Formats");
    }

    /**
     * Loads date formats from a file
     *
     * @since 2.1.0
     */
    protected static void loadDateFormats() {
        if (Objects.isNull(dateFormats) || dateFormats.isEmpty()) {
            dateFormats = DateTimeValidator.getFileFromResourceAsStream();
            dateFormats = dateFormats.stream().filter(DateTimeValidator::isValidDateFormatPattern).collect(Collectors.toList());
        }
    }

    /**
     * Gets all the date format patterns in a file
     *
     * @return the boolean flag to indicate validity of the supplied date format pattern text
     * @throws DateTimeValidationException thrown when datetime validation fails.
     * @since 2.1.0
     */
    protected static List<String> getFileFromResourceAsStream() {
        ClassLoader classLoader = DateTimeValidator.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILE_LOCATION);
        if (Objects.isNull(inputStream)) {
            throw new DateTimeValidationException("File not found -> " + FILE_LOCATION);
        } else {
            return new BufferedReader(new InputStreamReader(inputStream)).lines().map(String::trim).collect(Collectors.toList());
        }
    }

}
