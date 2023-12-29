package za.co.stridepace.dateutil.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeZoneDetail {
	
	private String id;
	
	private String displayName;
	
	private String abbreviation;
	
	private Integer offset;
	
    public static TimeZoneDetail getInstance(String id, String displayName, String abbreviation, Integer offset) {
    	return new TimeZoneDetail(id, displayName, abbreviation, offset);
    }
}
