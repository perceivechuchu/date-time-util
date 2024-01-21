package io.github.perceivechuchu.datetimeutil.validator;

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

    private static final String FILE_LOCATION = "datetime/formats/all-formats.txt";
    private static List<String> dateFormats;

    private DateTimeValidator() {
    }

    public static boolean isValid(String dateTimeText) {
        return isValid(dateTimeText, Locale.getDefault());
    }

    public static boolean isValid(String dateTimeText, Locale locale) {
        try {
            loadDateFormats();
            DateUtils.parseDateStrictly(dateTimeText, locale, dateFormats.toArray(new String[0]));
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValid(String dateTimeText, String dateFormatPattern) {
        return isValid(dateTimeText, Locale.getDefault(), dateFormatPattern);
    }

    public static boolean isValid(String dateTimeText, Locale locale, String dateFormatPattern) {
        try {
            loadDateFormats();
            DateUtils.parseDateStrictly(dateTimeText, locale, dateFormatPattern);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isValidDateFormatPattern(String fileEntry) {
        return StringUtils.isNotBlank(fileEntry) && !fileEntry.contains("_Formats");
    }

    protected static void loadDateFormats() {
        if (Objects.isNull(dateFormats) || dateFormats.isEmpty()) {
            dateFormats = DateTimeValidator.getFileFromResourceAsStream();
            dateFormats = dateFormats.stream().filter(DateTimeValidator::isValidDateFormatPattern).collect(Collectors.toList());
        }
    }

    protected static List<String> getFileFromResourceAsStream() {
        ClassLoader classLoader = DateTimeValidator.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILE_LOCATION);
        if (Objects.isNull(inputStream)) {
            throw new IllegalArgumentException("File not found -> " + FILE_LOCATION);
        } else {
            return new BufferedReader(new InputStreamReader(inputStream)).lines().map(String::trim).collect(Collectors.toList());
        }
    }

}
