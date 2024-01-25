package io.github.perceivechuchu.datetimeutil.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DateTimeValidationException is thrown when datetime validation fails
 *
 * @since 2.1.0
 *
 * @author Perceive Chuchu
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class DateTimeValidationException extends RuntimeException {

    /**
     * the message that is returned when the exception is thrown
     *
     * @since 2.1.0
     */
    private final String message;

    /**
     * the constructor that instantiates an instance of DateTimeValidationException
     *
     * @param message the message that is returned when the exception is thrown
     * @since 2.1.0
     */
    public DateTimeValidationException(String message) {
        super(message);
        this.message = message;
    }

}
