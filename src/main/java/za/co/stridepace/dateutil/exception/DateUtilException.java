package za.co.stridepace.dateutil.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class DateUtilException extends Exception {

    private final int code;
    private final String message;

    public DateUtilException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
