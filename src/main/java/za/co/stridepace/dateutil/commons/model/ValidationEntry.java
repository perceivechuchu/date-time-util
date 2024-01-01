package za.co.stridepace.dateutil.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ValidationEntry class holds the validation details to be used when validating parameter
 *
 * @since 1.0.0
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

    public static ValidationEntry getInstance(Object value, String message) {
        return new ValidationEntry(value, message);
    }
}
