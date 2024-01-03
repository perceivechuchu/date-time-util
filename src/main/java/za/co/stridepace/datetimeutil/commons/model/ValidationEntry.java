package za.co.stridepace.datetimeutil.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ValidationEntry class holds the value and message during parameter validation
 *
 * @since 1.0.0
 *
 * @author Perceive Chuchu
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationEntry {

    /**
     * The value to validate
     *
     * @since 1.0.0
     */
    private Object value;

    /**
     * The message to respond with when validation fails
     *
     * @since 1.0.0
     */
    private String message;

    /**
     * Instantiates new ValidationEntry object
     *
     * @param value the value to validate
     * @param message message to respond with when validation fails
     * @return the new ValidationEntry object
     * @since 1.0.0
     */
    public static ValidationEntry getInstance(Object value, String message) {
        return new ValidationEntry(value, message);
    }
}
