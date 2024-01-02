package za.co.stridepace.dateutil.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DateConversionException is thrown when date conversion fails
 *
 * @since 1.0.0
 *
 * @author Perceive Chuchu
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class DateConversionException extends RuntimeException {

    /**
     * the message that is returned when the exception is thrown
     *
     * @since 1.0.0
     */
    private final String message;

    /**
     * the constructor that instantiates an instance of DateConversionException
     *
     * @param message the message that is returned when the exception is thrown
     * @since 1.0.0
     */
    public DateConversionException(String message) {
        super(message);
        this.message = message;
    }

}
