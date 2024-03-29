package io.github.perceivechuchu.datetimeutil.commons.util;

import org.apache.commons.lang3.StringUtils;
import io.github.perceivechuchu.datetimeutil.commons.model.ValidationEntry;

import java.util.Objects;

/**
 * This class is a utility for validating input parameters
 *
 * @since 1.0.0
 *
 * @author Perceive Chuchu
 */
public final class ValidationUtil {

    private ValidationUtil() {
    }

    /**
     * Validates and rejects null or empty input parameter
     *
     * @param value   the value of the input
     * @param message message to return in case of the field being null or empty (This is optional)
     * @throws IllegalArgumentException if parameter is empty
     * @since 1.0.0
     */
    public static void rejectEmpty(Object value, String message) {
        if (Objects.isNull(value) || (value instanceof String && StringUtils.isBlank((String) value))) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Validates and rejects null or empty input parameters
     *
     * @param validationEntries the value of the input
     * @throws IllegalArgumentException if parameter is empty
     * @since 1.0.0
     */
    public static void rejectEmpty(ValidationEntry... validationEntries) {
        for (ValidationEntry entry : validationEntries) {
            rejectEmpty(entry.getValue(), entry.getMessage());
        }
    }

}
