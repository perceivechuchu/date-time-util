package za.co.stridepace.dateutil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TimeZoneDetail class holds the time zone details. The fields contain values transformed/mapped from java.util.TimeZone
 *
 * @since 1.0.0
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeZoneDetail {

    /**
     * The id for a time zone
     *
     * @since 1.0.0
     */
    private String id;

    /**
     * The display name for a time zone e.g. "Africa/Johannesburg"
     *
     * @since 1.0.0
     */
    private String displayName;

    /**
     * The abbreviation for a time zone e.g. "SAST"
     *
     * @since 1.0.0
     */
    private String abbreviation;

    /**
     * The difference in milliseconds from Coordinated Universal Time (UTC) e.g. "19800000"
     *
     * @since 1.0.0
     */
    private Integer offsetMillis;

    /**
     * The difference in hours and minutes from Coordinated Universal Time (UTC) e.g. "+02:00"
     *
     * @since 1.0.0
     */
    private String offsetText;

    /**
     * Gets an instance of a time zone detail
     *
     * @param id           the id for a time zone
     * @param displayName  the display name for a time zone e.g. "Africa/Johannesburg"
     * @param abbreviation the abbreviation for a time zone e.g. "SAST"
     * @param offsetMillis the difference in milliseconds from Coordinated Universal Time (UTC)
     * @param offsetText   the difference in hours and minutes from Coordinated Universal Time (UTC)
     * @return the new time zone detail that has been instantiated
     * @since 1.0.0
     */
    public static TimeZoneDetail getInstance(String id, String displayName, String abbreviation, Integer offsetMillis, String offsetText) {
        return new TimeZoneDetail(id, displayName, abbreviation, offsetMillis, offsetText);
    }
}
