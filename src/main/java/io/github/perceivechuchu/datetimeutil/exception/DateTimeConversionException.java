package io.github.perceivechuchu.datetimeutil.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DateTimeConversionException is thrown when date conversion fails
 *
 * @since 1.0.0
 *
 * @author Perceive Chuchu
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class DateTimeConversionException extends RuntimeException {

    /**
     * the message that is returned when the exception is thrown
     *
     * @since 1.0.0
     */
    private final String message;

    /**
     * the constructor that instantiates an instance of DateTimeConversionException
     *
     * @param message the message that is returned when the exception is thrown
     * @since 1.0.0
     */
    public DateTimeConversionException(String message) {
        super(message);
        this.message = message;
    }

}
